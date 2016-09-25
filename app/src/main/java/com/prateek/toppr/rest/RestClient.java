package com.prateek.toppr.rest;

import com.prateek.toppr.rest.Response.EventsList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public interface RestClient {

    String end_point = "https://hackerearth.0x10.info/";

    String GET_EVENTS = "/api/toppr_events?type=json&query=list_events";

    @GET(GET_EVENTS)
    Call<EventsList> fetchEvents();

    class Implementation {

        private static Retrofit retrofit;
        private static RestClient restClient;

        public static RestClient getClient() {

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(end_point)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            if (restClient == null) {
                restClient = retrofit.create(RestClient.class);
            }

            return restClient;
        }
    }
}