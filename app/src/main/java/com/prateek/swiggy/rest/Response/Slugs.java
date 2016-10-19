package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

public class Slugs implements Parcelable {

    public static final Parcelable.Creator<Slugs> CREATOR = new Parcelable.Creator<Slugs>() {
        @Override
        public Slugs createFromParcel(Parcel source) {
            return new Slugs(source);
        }

        @Override
        public Slugs[] newArray(int size) {
            return new Slugs[size];
        }
    };
    private String restaurant;
    private String city;

    public Slugs() {
    }

    protected Slugs(Parcel in) {
        this.restaurant = in.readString();
        this.city = in.readString();
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Slugs{" +
                "restaurant='" + restaurant + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.restaurant);
        dest.writeString(this.city);
    }
}
