package com.example.medialibrary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medialibrary.R;
import com.example.medialibrary.model.LibSample;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.songviewholder> {
    private ArrayList<LibSample> libSongs;

    public SongAdapter(ArrayList<LibSample> libSongs) {
        this.libSongs = libSongs;
    }

    @NonNull
    @Override
    public SongAdapter.songviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);

        return new songviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.songviewholder holder, int position) {
        LibSample sample = libSongs.get(position);

        holder.container.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_transition));

//         holder.bandImg.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_transition));

        holder.songName.setText(sample.getSong());


    }

    @Override
    public int getItemCount() {
        if (libSongs != null){
            return libSongs.size();
        }
        return 0;
    }

    public class songviewholder extends RecyclerView.ViewHolder {
        TextView songName;
        RelativeLayout container;


        public songviewholder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.relativeLaySong);
            songName = itemView.findViewById(R.id.song_name);

        }
    }
}
