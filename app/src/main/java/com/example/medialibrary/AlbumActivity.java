package com.example.medialibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.medialibrary.adapters.AlbumAdapter;
import com.example.medialibrary.model.LibSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity implements AlbumAdapter.OnAlbumListener {

    private RecyclerView rvAlbums;
    private AlbumAdapter albumAdapter;
    ArrayList<LibSample> libAlbums = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Intent intent = getIntent();
        LibSample libSample = intent.getParcelableExtra("album");

        String bands = libSample.getBand();

        libAlbums = null;

        try{
            libAlbums = initAlbums(bands);
            initViews();
            albumAdapter = new AlbumAdapter(libAlbums, this);
            rvAlbums.setAdapter(albumAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initViews() {
        rvAlbums = findViewById(R.id.rv_albums);
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));
        rvAlbums.setHasFixedSize(true);
    }

    @SuppressLint("NewApi")
    private ArrayList<LibSample> initAlbums(String band) throws IOException {
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
            String bandName = tokens[0];
            String albumName = tokens[1];
            String albumImgName;
            if(albumName.contains(" ")){
                albumImgName = albumName.replace(" ", "_").toLowerCase();
            } else{
                albumImgName = albumName.toLowerCase();
            }

            if (bandName.equals(band)){
                String uri = "@drawable/" +  albumImgName;
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                sample.setAlbum(albumName);
                sample.setDrawableResource(imageResource);
                if (!(list.stream().map(LibSample::getAlbum).filter(albumName::equals).findFirst().isPresent()))
                    list.add(sample);
            }

        }
        return list;
    }


    @Override
    public void onAlbumClick(int position) {
        libAlbums.get(position);
        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("song", libAlbums.get(position));
        startActivity(intent);
    }
}