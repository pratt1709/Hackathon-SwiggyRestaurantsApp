package com.prateek.toppr.rest.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class EventsListRequest implements Parcelable {

    public static final Parcelable.Creator<EventsListRequest> CREATOR = new Parcelable.Creator<EventsListRequest>() {
        @Override
        public EventsListRequest createFromParcel(Parcel source) {
            return new EventsListRequest(source);
        }

        @Override
        public EventsListRequest[] newArray(int size) {
            return new EventsListRequest[size];
        }
    };
    private ArrayList<Event> websites;
    @SerializedName("quote_max")
    private String totalQuote;
    @SerializedName("quote_available")
    private String availableQuote;

    public EventsListRequest() {
    }

    protected EventsListRequest(Parcel in) {
        this.websites = in.createTypedArrayList(Event.CREATOR);
        this.totalQuote = in.readString();
        this.availableQuote = in.readString();
    }

    public Event get(int position) {

        if (websites != null && position < websites.size()) {
            return websites.get(position);
        } else {
            return null;
        }
    }

    public int size() {
        if (websites != null) {
            return websites.size();
        } else {
            return 0;
        }
    }

    public void setWebsites(ArrayList<Event> web) {
        websites = web;
    }

    public String getTotalQuote() {
        return totalQuote;
    }

    public String getAvailableQuote() {
        return availableQuote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.websites);
        dest.writeString(this.totalQuote);
        dest.writeString(this.availableQuote);
    }
}


