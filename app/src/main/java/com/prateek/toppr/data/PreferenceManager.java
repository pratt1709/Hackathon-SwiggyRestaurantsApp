package com.prateek.toppr.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.prateek.toppr.TopprApp;
import com.prateek.toppr.rest.Response.EventsList;
import com.prateek.toppr.rest.Favourites;


/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class PreferenceManager {

    public static final String KEY_EVENTS = "key_events";
    public static final String KEY_FAVOURITES = "key_favourites";

    private static final String PREFS_NAME = "app_prefs_name";

    public static void recordEvents(EventsList eventsList) {
        Gson gson = new Gson();
        String json = gson.toJson(eventsList);
        setString(KEY_EVENTS, json);
    }

    public static EventsList fetchEvents() {
        String value = getString(KEY_EVENTS);

        EventsList eventsList = null;
        if (value != null) {
            Gson gson = new Gson();
            eventsList = gson.fromJson(value, EventsList.class);
        }

        return eventsList;
    }

    public static void addFavourites(String id) {
        Favourites favourites = PreferenceManager.fetchFavourites();
        if (favourites == null) {
            favourites = new Favourites();
        }

        favourites.addFavourite(id);

        PreferenceManager.recordFavourites(favourites);
    }

    public static void removeFavourites(String id) {
        Favourites favourites = PreferenceManager.fetchFavourites();
        if (favourites == null) {
            favourites = new Favourites();
        }

        favourites.removeFavourites(id);

        PreferenceManager.recordFavourites(favourites);
    }

    public static Favourites fetchFavourites() {
        String value = getString(KEY_FAVOURITES);

        Favourites favourites = null;
        if (value != null) {
            Gson gson = new Gson();
            favourites = gson.fromJson(value, Favourites.class);
        }

        return favourites;
    }

    public static void recordFavourites(Favourites favourites) {
        Gson gson = new Gson();
        String json = gson.toJson(favourites);
        setString(KEY_FAVOURITES, json);
    }

    private static void setString(String key, String value) {
        SharedPreferences.Editor editor = TopprApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        SharedPreferences prefs = TopprApp.getContext()
                .getSharedPreferences(
                        PREFS_NAME,
                        Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }
}
