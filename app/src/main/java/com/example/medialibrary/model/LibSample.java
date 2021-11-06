package com.example.medialibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LibSample implements Parcelable {
    String band;
    String album;
    String song;
    private int drawableResource; // this for testing;


    public LibSample() {
    }

    public LibSample(int drawableResource) {
        this.drawableResource = drawableResource;
    }

    protected LibSample(Parcel in) {
        band = in.readString();
        album = in.readString();
        song = in.readString();
        drawableResource = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(band);
        dest.writeString(album);
        dest.writeString(song);
        dest.writeInt(drawableResource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LibSample> CREATOR = new Creator<LibSample>() {
        @Override
        public LibSample createFromParcel(Parcel in) {
            return new LibSample(in);
        }

        @Override
        public LibSample[] newArray(int size) {
            return new LibSample[size];
        }
    };

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource(int drawableResource) {
        this.drawableResource = drawableResource;
    }

    public LibSample(String band, String album, String song) {
        this.band = band;
        this.album = album;
        this.song = song;
//        this.drawableResource = drawableResource;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "LibSample{" +
                "band='" + band + '\'' +
                ", album='" + album + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}
