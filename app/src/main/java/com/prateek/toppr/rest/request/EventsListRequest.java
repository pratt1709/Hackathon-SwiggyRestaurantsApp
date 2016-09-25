package com.prateek.toppr.rest.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class EventsListRequest {

    private ArrayList<Event> websites;

    @SerializedName("quote_max")
    private String totalQuote;

    @SerializedName("quote_available")
    private String availableQuote;

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
}


