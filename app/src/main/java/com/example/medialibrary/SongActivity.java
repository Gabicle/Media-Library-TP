package com.example.medialibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.medialibrary.adapters.AlbumAdapter;
import com.example.medialibrary.adapters.SongAdapter;
import com.example.medialibrary.model.LibSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    private RecyclerView rvSongs;
    private SongAdapter songAdapter;
    ArrayList<LibSample> libSongs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();
        LibSample libSample = intent.getParcelableExtra("song");
        String albums = libSample.getAlbum();

        libSongs = null;

        try{
            libSongs = initSongs(albums);
            initViews();
            songAdapter = new SongAdapter(libSongs);
            rvSongs.setAdapter(songAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        rvSongs = findViewById(R.id.rv_songs);
        rvSongs.setLayoutManager(new LinearLayoutManager(this));
        rvSongs.setHasFixedSize(true);
    }

    private ArrayList<LibSample> initSongs(String album) throws IOException {
        ArrayList<LibSample> list = new ArrayList<>();
        InputStream is =  getResources().openRawResource(R.raw.library);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line;
        //step over header
        reader.readLine();

        while ((line = reader.readLine()) != null){
            String[] tokens = line.split(",");
            LibSample sample = new LibSample();
            String albumName = tokens[1];
            String songName = tokens[2];
            if (albumName.equals(album)){
                sample.setSong(songName);
                list.add(sample);
            }

        }
        return list;

    }
}