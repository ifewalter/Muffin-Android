package com.utiset.muffin.muffin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.utiset.muffin.core.FeedsCall;
import com.utiset.muffin.data.FeedData;
import com.utiset.muffin.exceptions.NullContextExceptions;
import com.utiset.muffin.models.FeedItemModel;
import com.utiset.muffin.models.FeedResponseModel;
import com.utiset.muffin.newsfeed.ExpandableHeightListView;
import com.utiset.muffin.newsfeed.ListBaseAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class FeedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ExpandableHeightListView listview;
    private ArrayList<FeedItemModel> feedItemModels;
    private ListBaseAdapter baseAdapter;

    private ProgressDialog loadingDialog;

    FeedsCall feedsCall;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("Fetching feeds");
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadingDialog.setIndeterminate(true);
        loadingDialog.setProgress(0);
        loadingDialog.setMax(100);


        try {
            feedsCall = new FeedsCall(this.getApplicationContext());
        } catch (NullContextExceptions nullContextExceptions) {
            nullContextExceptions.printStackTrace();
        }

        loadingDialog.show();

        new FeedTask().execute();


        listview = (ExpandableHeightListView)findViewById(R.id.listview);

        feedItemModels = new ArrayList<>();

        baseAdapter = new ListBaseAdapter(FeedActivity.this, feedItemModels) {
        };

        listview.setAdapter(baseAdapter);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage){
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }


    public class FeedTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                feedsCall.getRecentFeeds("http://198.74.52.162:5000/feed/recent");

                FeedData feedData = new FeedData(getApplicationContext());
                ArrayList<FeedItemModel> feedResult = feedData.getData();

                int counter = 0;

                for (FeedItemModel feedItemModel:feedResult) {

                    loadingDialog.setProgress((counter / feedResult.size()) * 100);

                    Bitmap mIcon11 = null;
                    try {
                        InputStream in = new java.net.URL(feedItemModel.getMain_image()).openStream();
                        mIcon11 = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                    feedItemModel.setMainImageContainer(mIcon11);

                    ++counter;

                }
                feedItemModels.addAll(feedResult);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            loadingDialog.hide();
            baseAdapter.notifyDataSetChanged();
        }
    }


}
