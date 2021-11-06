package com.example.medialibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.medialibrary.model.LibSample;
import com.example.medialibrary.R;

import java.util.ArrayList;

public class BandAdapter extends RecyclerView.Adapter<BandAdapter.bandviewholder> {
    private ArrayList<LibSample> libSamples;
    private OnBandListener mOnBandListener;
    private Context mContext;

    public BandAdapter(ArrayList<LibSample> libSamples, OnBandListener onBandListener) {
        this.libSamples = libSamples;
        this.mOnBandListener = onBandListener;
    }

    @NonNull
    @Override
    public bandviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_band, parent, false);

        return new bandviewholder(v, mOnBandListener);
    }

    @Override
    public void onBindViewHolder(@NonNull bandviewholder holder, int position) {
        LibSample sample = libSamples.get(position);

//        holder.bandImg.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_transition));

        holder.name.setText(sample.getBand());
        holder.bandImg.setImageResource(sample.getDrawableResource());

//        Glide.with(holder.itemView.getContext())
//                .load(libSamples.get(position).getDrawableResource())
//                .transform(new CenterCrop(), new RoundedCorners(16))
//                .into(holder.bandImg);


    }

    @Override
    public int getItemCount() {
        if (libSamples != null){
            return libSamples.size();
        }
        return 0;
    }

    public class bandviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView bandImg;
        TextView name;
        OnBandListener onBandListener;

        public bandviewholder(@NonNull View view, OnBandListener onBandListener) {
            super(view);
            name = view.findViewById(R.id.band_name);
            bandImg = view.findViewById(R.id.band_img);
            this.onBandListener = onBandListener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onBandListener.onBandClick(getAdapterPosition());
        }
    }

    public interface OnBandListener {
        void onBandClick(int position);
    }
}
