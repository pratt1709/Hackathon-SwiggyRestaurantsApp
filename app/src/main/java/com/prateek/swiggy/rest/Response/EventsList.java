package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.prateek.swiggy.rest.Event;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class EventsList implements Parcelable, Comparable {

    public static final Parcelable.Creator<EventsList> CREATOR = new Parcelable.Creator<EventsList>() {
        @Override
        public EventsList createFromParcel(Parcel source) {
            return new EventsList(source);
        }

        @Override
        public EventsList[] newArray(int size) {
            return new EventsList[size];
        }
    };

    private ArrayList<Event> websites;

    @SerializedName("quote_max")
    private String totalQuote;

    @SerializedName("quote_available")
    private String availableQuote;

    public EventsList() {
    }

    protected EventsList(Parcel in) {
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

    public ArrayList<Event> getWebsites() {
        return websites;
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

    @Override
    public int compareTo(Object another) {
        return 0;
    }

}


