package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    public Data() {
    }

    protected Data(Parcel in) {
        this.restaurants = in.createTypedArrayList(Restaurant.CREATOR);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public String toString() {
        return "Data{" +
                "restaurants=" + restaurants +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.restaurants);
    }
}
