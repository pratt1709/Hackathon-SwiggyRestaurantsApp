package com.prateek.swiggy.rest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class Favourites implements Parcelable {

    public static final Parcelable.Creator<Favourites> CREATOR = new Parcelable.Creator<Favourites>() {
        @Override
        public Favourites createFromParcel(Parcel source) {
            return new Favourites(source);
        }

        @Override
        public Favourites[] newArray(int size) {
            return new Favourites[size];
        }
    };
    private ArrayList<String> idList;

    public Favourites() {
        idList = new ArrayList<>();
    }

    public Favourites(ArrayList<String> idList) {
        if (idList != null) {
            this.idList = idList;
        } else {
            this.idList = new ArrayList<>();
        }
    }

    protected Favourites(Parcel in) {
        this.idList = in.createStringArrayList();
    }

    public ArrayList<String> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<String> idList) {
        this.idList = idList;
    }

    public void addFavourite(String id) {
        idList.add(id);
    }

    public void removeFavourites(String id) {
        idList.remove(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.idList);
    }
}
