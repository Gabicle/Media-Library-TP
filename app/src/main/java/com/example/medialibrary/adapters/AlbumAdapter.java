package com.example.medialibrary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.medialibrary.R;
import com.example.medialibrary.model.LibSample;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.albumviewholder> {
    private ArrayList<LibSample> libAlbum;
    private OnAlbumListener mOnAlbumListener;

    public AlbumAdapter(ArrayList<LibSample> libAlbum, OnAlbumListener onAlbumListener) {
        this.libAlbum = libAlbum;
        this.mOnAlbumListener = onAlbumListener;
    }

    @NonNull
    @Override
    public AlbumAdapter.albumviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);

        return new albumviewholder(v, mOnAlbumListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.albumviewholder holder, int position) {
        LibSample sample = libAlbum.get(position);

        holder.albumImg.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_transition));
        holder.container.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_scale_animation));


        holder.albumName.setText(sample.getAlbum());
        holder.albumImg.setImageResource(sample.getDrawableResource());


//        Glide.with(holder.itemView.getContext())
//                .load(libAlbum.get(position).getDrawableResource())
//                .transform(new CenterCrop(), new RoundedCorners(16))
//                .into(holder.albumImg);

    }

    @Override
    public int getItemCount() {
        if (libAlbum != null){
            return libAlbum.size();
        }
        return 0;
    }

    public class albumviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
       ImageView albumImg;
        TextView albumName;
        OnAlbumListener onAlbumListener;
        RelativeLayout container;


        public albumviewholder(@NonNull View itemView, OnAlbumListener onAlbumListener) {
            super(itemView);
            container = itemView.findViewById(R.id.relativeLayAlbum);
            albumName = itemView.findViewById(R.id.album_name);
            albumImg = itemView.findViewById(R.id.album_img);
            this.onAlbumListener = onAlbumListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onAlbumListener.onAlbumClick(getAdapterPosition());
        }
    }

    public interface OnAlbumListener{
        void onAlbumClick(int position);
    }


}
