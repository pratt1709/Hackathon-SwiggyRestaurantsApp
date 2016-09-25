package com.prateek.toppr.ui.activities;

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

import com.prateek.toppr.R;
import com.prateek.toppr.data.PreferenceManager;
import com.prateek.toppr.rest.Event;
import com.prateek.toppr.rest.Favourites;
import com.prateek.toppr.rest.Response.EventsList;
import com.prateek.toppr.rest.RestClient;
import com.prateek.toppr.ui.adapter.EventsListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ProgressDialog mProgressDialog;

    private RecyclerView recyclerView;
    private EventsListAdapter mAdapter;

    private EventsList mEventsList;

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

        mEventsList = PreferenceManager.fetchEvents();
        if (mEventsList == null) {
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
                                mEventsList = PreferenceManager.fetchEvents();
                                updateView();
                                Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_favourites:
                                // MainActivity.this.startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                                displayFavourite();
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
            mAdapter = new EventsListAdapter();
            recyclerView.setAdapter(mAdapter);
        }

        // Refresh Adapter
        mAdapter.refreshWithData(mEventsList);
    }

    // This is kind of hack to save time
    private void displayFavourite() {
        Favourites favourites = PreferenceManager.fetchFavourites();

        ArrayList<Event> events = new ArrayList<>();

        for (int count = 0; count < mEventsList.size(); count++) {
            Event event = mEventsList.get(count);
            if (favourites.getIdList().contains(event.getId())) {
                events.add(event);
            }
        }

        EventsList list = new EventsList();
        list.setWebsites(events);

        mEventsList = list;

        updateView();
    }

    private void eventsCall() {

        mProgressDialog.show();

        // Initialize the task to fetch events
        RestClient.Implementation.getClient().fetchEvents().enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(Call<EventsList> call, Response<EventsList> response) {
                mEventsList = response.body();

                MainActivity.this.updateView();

                // Save Data
                PreferenceManager.recordEvents(mEventsList);

                Snackbar.make(recyclerView, R.string.request_success, Snackbar.LENGTH_LONG).show();
                mProgressDialog.hide();
            }

            @Override
            public void onFailure(Call<EventsList> call, Throwable t) {
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
