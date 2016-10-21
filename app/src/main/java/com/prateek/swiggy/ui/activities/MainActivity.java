package com.prateek.swiggy.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.prateek.swiggy.R;
import com.prateek.swiggy.data.PreferenceManager;
import com.prateek.swiggy.rest.Response.RestaurantsList;
import com.prateek.swiggy.rest.Response.RestaurantsResponse;
import com.prateek.swiggy.rest.RestClient;
import com.prateek.swiggy.ui.adapter.RestaurantsListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ProgressDialog mProgressDialog;

    private RecyclerView recyclerView;
    private RestaurantsListAdapter mAdapter;

    private RestaurantsList mRestarantsList;

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

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        mRestarantsList = PreferenceManager.fetchRestaurants();
        if (mRestarantsList == null) {
            this.eventsCall();
        } else {
            updateView();
        }
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
                                // mRestarantsList = PreferenceManager.fetchEvents();
                                updateView();
                                Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_favourites:
                                // MainActivity.this.startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                                // displayFavourite();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_list, menu);
        return true;
    }

    private void updateView() {
        if (mAdapter == null) {
            mAdapter = new RestaurantsListAdapter();
            recyclerView.setAdapter(mAdapter);
        }

        // Refresh Adapter
        mAdapter.refreshWithData(mRestarantsList);
    }

    private void eventsCall() {

        mProgressDialog.show();

        // Initialize the task to fetch events
        RestClient.Implementation.getClient().fetchRestaurants().enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {

                if (response.body() != null && response.body().getRestaurantsList() != null) {
                    mRestarantsList = response.body().getRestaurantsList();
                }

                MainActivity.this.updateView();

                // Save RestaurantsList
                PreferenceManager.recordRestaurants(mRestarantsList);

                Snackbar.make(recyclerView, R.string.request_success, Snackbar.LENGTH_LONG).show();
                mProgressDialog.hide();
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                Snackbar.make(recyclerView, R.string.request_error, Snackbar.LENGTH_LONG).show();
                mProgressDialog.hide();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_refresh:
                this.eventsCall();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
