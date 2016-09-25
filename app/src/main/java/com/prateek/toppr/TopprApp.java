package com.prateek.toppr;

import android.app.Application;
import android.content.Context;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class TopprApp extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
