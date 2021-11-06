package com.example.medialibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.example.medialibrary.adapters.BandAdapter;
import com.example.medialibrary.model.LibSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;

public class BandActivity extends AppCompatActivity implements BandAdapter.OnBandListener {

    private RecyclerView rvBands;
    private BandAdapter bandAdapter;
    ArrayList<LibSample> libSamples = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);

         libSamples = null;

        try {
            libSamples = initBands();
            initViews();
            bandAdapter = new BandAdapter(libSamples, this);
            rvBands.setAdapter(bandAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void initViews() {
        rvBands = findViewById(R.id.rv_bands);
        rvBands.setLayoutManager(new LinearLayoutManager(this));
        rvBands.setHasFixedSize(true);
    }




    @SuppressLint("NewApi")
    private ArrayList<LibSample> initBands() throws IOException {
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
            String bandImgName;
            if (bandName.contains(" ")){
                bandImgName  = bandName.replace(" ", "_").toLowerCase();

            } else{
                bandImgName = bandName.toLowerCase();
            }
            String uri = "@drawable/" +  bandImgName;
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            sample.setBand(bandName);
            sample.setDrawableResource(imageResource);

            if (!(list.stream().map(LibSample::getBand).filter(bandName::equals).findFirst().isPresent()))
                list.add(sample);
        }



        return list;
    }

    @Override
    public void onBandClick(int position) {
        Log.d("Test", "clicked");
        libSamples.get(position);
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra("album", libSamples.get(position));
        startActivity(intent);
    }
}