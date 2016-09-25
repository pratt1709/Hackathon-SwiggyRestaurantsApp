package com.prateek.toppr.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.prateek.toppr.R;
import com.prateek.toppr.rest.RestClient;
import com.prateek.toppr.rest.request.EventsListRequest;
import com.prateek.toppr.ui.adapter.EventsListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ProgressDialog mProgressDialog;

    private RecyclerView recyclerView;
    private EventsListAdapter mAdapter;

    private EventsListRequest mEventsListRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.request_progress));
        mProgressDialog.setCancelable(false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Initialize the task to fetch events
        RestClient.Implementation.getClient().fetchEvents().enqueue(new Callback<EventsListRequest>() {
            @Override
            public void onResponse(Call<EventsListRequest> call, Response<EventsListRequest> response) {
                mEventsListRequest = response.body();

                mAdapter = new EventsListAdapter(mEventsListRequest);
                recyclerView.setAdapter(mAdapter);

                mProgressDialog.hide();
            }

            @Override
            public void onFailure(Call<EventsListRequest> call, Throwable t) {
                Snackbar.make(recyclerView, R.string.request_error, Snackbar.LENGTH_SHORT).show();
                mProgressDialog.hide();
            }
        });

        mProgressDialog.show();

    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        // First uncheck all menu items
                        Menu menu = navigationView.getMenu();
                        menu.size();

                        for (int count = 0; count < menu.size(); count++) {
                            MenuItem item = menu.getItem(count);
                            if (item != null) {
                                item.setChecked(false);
                            }
                        }

                        menuItem.setChecked(true);

                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_favourites:
                                MainActivity.this.startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                                Toast.makeText(MainActivity.this, "FAV", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_statistics:
                                Toast.makeText(MainActivity.this, "Stats", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
