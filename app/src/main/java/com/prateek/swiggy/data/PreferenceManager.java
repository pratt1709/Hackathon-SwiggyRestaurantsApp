package com.prateek.swiggy.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.prateek.swiggy.SwiggyApp;
import com.prateek.swiggy.rest.Response.RestaurantsList;


/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class PreferenceManager {

    public static final String KEY_RESTAURANTS = "key_restaurants";

    private static final String PREFS_NAME = "app_prefs_name";

    public static void recordRestaurants(RestaurantsList restaurantsList) {
        Gson gson = new Gson();
        String json = gson.toJson(restaurantsList);
        setString(KEY_RESTAURANTS, json);
    }

    public static RestaurantsList fetchRestaurants() {
        String value = getString(KEY_RESTAURANTS);

        RestaurantsList restaurantsList = null;
        if (value != null) {
            Gson gson = new Gson();
            restaurantsList = gson.fromJson(value, RestaurantsList.class);
        }

        return restaurantsList;
    }

    private static void setString(String key, String value) {
        SharedPreferences.Editor editor = SwiggyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(String key) {
        SharedPreferences prefs = SwiggyApp.getContext()
                .getSharedPreferences(
                        PREFS_NAME,
                        Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }
}
