package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsList implements Parcelable {

    public static final Parcelable.Creator<RestaurantsList> CREATOR = new Parcelable.Creator<RestaurantsList>() {
        @Override
        public RestaurantsList createFromParcel(Parcel source) {
            return new RestaurantsList(source);
        }

        @Override
        public RestaurantsList[] newArray(int size) {
            return new RestaurantsList[size];
        }
    };
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    public RestaurantsList() {
    }

    protected RestaurantsList(Parcel in) {
        this.restaurants = in.createTypedArrayList(Restaurant.CREATOR);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public String toString() {
        return "RestaurantsList{" +
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

    public Restaurant get(int index) {
        if (restaurants != null) {
            return this.restaurants.get(index);
        }
        return null;
    }

    public int size() {
        if (restaurants != null) {
            return restaurants.size();
        }
        return 0;

    }
}
