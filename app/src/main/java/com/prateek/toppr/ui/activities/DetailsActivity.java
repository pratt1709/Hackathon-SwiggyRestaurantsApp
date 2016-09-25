package com.prateek.toppr.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.prateek.toppr.R;
import com.prateek.toppr.rest.request.Event;
import com.squareup.picasso.Picasso;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String INTENT_EVENT_DETAIL = "intent_event_detail";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        Event event = intent.getParcelableExtra(INTENT_EVENT_DETAIL);

        if (event != null) {

            CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            toolbarLayout.setTitle(event.getName());

            ImageView imgBackdrop = (ImageView) findViewById(R.id.img_backdrop);
            Picasso.with(this)
                    .load(event.getImagePath())
                    .placeholder(R.drawable.img_title)
                    .into(imgBackdrop);

            TextView txtExperience = (TextView) findViewById(R.id.txt_detail_experience);
            txtExperience.setText(event.getExperience());

            TextView txtCategory = (TextView) findViewById(R.id.txt_detail_category);
            txtCategory.setText(event.getCategory());

            TextView txtDescription = (TextView) findViewById(R.id.txt_detail_description);
            txtDescription.setText(event.getDescription());
        }
    }
}
