package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RestaurantsResponse implements Parcelable {

    private int statusCode;
    private String statusMessage;

    @SerializedName("data")
    private RestaurantsList restaurantsList;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public RestaurantsList getRestaurantsList() {
        return restaurantsList;
    }

    @Override
    public String toString() {
        return "RestaurantsResponse{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", restaurantsList=" + restaurantsList +
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
        dest.writeParcelable(this.restaurantsList, flags);
    }

    public RestaurantsResponse() {
    }

    protected RestaurantsResponse(Parcel in) {
        this.statusCode = in.readInt();
        this.statusMessage = in.readString();
        this.restaurantsList = in.readParcelable(RestaurantsList.class.getClassLoader());
    }

    public static final Parcelable.Creator<RestaurantsResponse> CREATOR = new Parcelable.Creator<RestaurantsResponse>() {
        @Override
        public RestaurantsResponse createFromParcel(Parcel source) {
            return new RestaurantsResponse(source);
        }

        @Override
        public RestaurantsResponse[] newArray(int size) {
            return new RestaurantsResponse[size];
        }
    };
}
