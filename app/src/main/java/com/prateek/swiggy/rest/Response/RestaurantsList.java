package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantsList implements Parcelable {

    private int statusCode;
    private String statusMessage;
    private Data data;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RestaurantsList{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.statusCode);
        dest.writeString(this.statusMessage);
        dest.writeParcelable(this.data, flags);
    }

    public RestaurantsList() {
    }

    protected RestaurantsList(Parcel in) {
        this.statusCode = in.readInt();
        this.statusMessage = in.readString();
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

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
}
