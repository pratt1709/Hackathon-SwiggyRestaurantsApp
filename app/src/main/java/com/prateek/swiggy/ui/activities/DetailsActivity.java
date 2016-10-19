package com.prateek.swiggy.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prateek.swiggy.R;
import com.prateek.swiggy.rest.Event;
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
        final Event event = intent.getParcelableExtra(INTENT_EVENT_DETAIL);

        if (event != null) {

            CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            toolbarLayout.setTitle(event.getName());

            ImageView imgBackdrop = (ImageView) findViewById(R.id.img_backdrop);
            Picasso.with(this)
                    .load(event.getImagePath())
                    .placeholder(R.drawable.img_title)
                    .into(imgBackdrop);

            String exp = event.getExperience();
            if (!TextUtils.isEmpty(exp)) {
                TextView txtExperience = (TextView) findViewById(R.id.txt_detail_experience);
                txtExperience.setText(String.format(getResources().getString(R.string.experience), exp));
            }

            String category = event.getCategory();
            if (!TextUtils.isEmpty(category)) {
                TextView txtCategory = (TextView) findViewById(R.id.txt_detail_category);
                txtCategory.setText(String.format(getResources().getString(R.string.category), category));
            }

            if (!TextUtils.isEmpty(event.getDescription())) {
                TextView txtDescription = (TextView) findViewById(R.id.txt_detail_description);
                txtDescription.setText(event.getDescription());
            }

            FloatingActionButton fabShare = (FloatingActionButton) findViewById(R.id.fab_share);
            fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/html");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, event.getSharableText());
                    startActivity(Intent.createChooser(sharingIntent, "@string/share_chooser"));
                }
            });


        }
    }
}
