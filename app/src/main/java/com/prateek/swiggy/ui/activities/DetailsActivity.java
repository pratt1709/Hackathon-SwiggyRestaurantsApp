package com.prateek.swiggy.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.prateek.swiggy.R;
import com.prateek.swiggy.rest.Response.Restaurant;

import java.util.List;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String INTENT_RESTAURANT_DETAIL = "intent_restaurant_detail";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = this.getIntent();
        final Restaurant restaurant = intent.getParcelableExtra(INTENT_RESTAURANT_DETAIL);

        TextView txtRestaurantCuisine = (TextView) this.findViewById(R.id.item_txt_restaurant_cuisine);
        TextView txtRestaurantPrice = (TextView) this.findViewById(R.id.item_text_price);
        TextView txtRestaurantRating = (TextView) this.findViewById(R.id.item_text_rating);
        TextView txtOpeningTime = (TextView) this.findViewById(R.id.item_txt_restaurant_opening_info);
        TextView txtOutletCount = (TextView) this.findViewById(R.id.item_txt_restaurant_outlet_info);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(restaurant.getName());


        List<String> cuisineList = restaurant.getCuisine();
        String cuisineText = "";

        if (cuisineList != null && cuisineList.size() > 0) {
            cuisineText = cuisineList.get(0);
        }

        for (int count = 1; count < cuisineList.size(); count++) {
            cuisineText = cuisineText + ", " + cuisineList.get(count);
        }

        txtRestaurantCuisine.setText(cuisineText);

        txtRestaurantRating.setText("" + restaurant.getAvgRating());

        txtOpeningTime.setText(restaurant.getNextOpenMessage());

        txtOutletCount.setText(String.format(this.getResources().getString(R.string.outlets_around), "" + restaurant.getChain().size()));

        txtRestaurantPrice.setText(this.getResources().getString(R.string.price) + " " +
                restaurant.getCostForTwoString() + restaurant.getCostForTwo());


        if (restaurant != null) {
            FloatingActionButton fabShare = (FloatingActionButton) findViewById(R.id.fab_share);
            fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/html");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, restaurant.getSharableText());
                    startActivity(Intent.createChooser(sharingIntent, "@string/share_chooser"));
                }
            });
        }
    }
}
