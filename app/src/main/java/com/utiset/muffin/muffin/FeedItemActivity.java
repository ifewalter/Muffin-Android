package com.utiset.muffin.muffin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.AnimRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ImageView;

import com.thefinestartist.finestwebview.FinestWebView;
import com.utiset.muffin.customfonts.MyEditText;
import com.utiset.muffin.customfonts.MyLightTextView;
import com.utiset.muffin.customfonts.MyTextView;
import com.utiset.muffin.data.FeedData;
import com.utiset.muffin.models.FeedItemModel;

import java.io.InputStream;
import java.net.CacheRequest;

import okhttp3.Cache;

public class FeedItemActivity extends AppCompatActivity {

    private ImageView mainImage;
    private MyTextView title ;
    private MyLightTextView body;
    private FeedItemModel feedItemModel;
    private Button readMoreButton;
    private Bundle activityBundle;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_item);

        activityBundle = new Bundle();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getExtras().getInt("ID");

        if (id == null || id == 0)
        {
            this.finish();
        }


        mainImage = (ImageView) findViewById(R.id.feed_item_newsimage);
        title = (MyTextView) findViewById(R.id.feed_item_title);
        body = (MyLightTextView) findViewById(R.id.feed_item_body);
        readMoreButton = (Button) findViewById(R.id.feed_item_read_more);

        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FinestWebView.Builder(getApplicationContext())
                        .webViewJavaScriptEnabled(true)
                        .backPressToClose(true)
                        .webViewAppCacheEnabled(true)
                        .webViewCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
                        .webViewDesktopMode(false)
                        .titleDefault(feedItemModel.getTitle())
                        .showProgressBar(false)
                        .show(feedItemModel.getUrl());
            }
        });

        new LoadData().execute();

    }


    private class LoadData extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            FeedData feedData = new FeedData(getApplicationContext());
            feedItemModel = feedData.getData(id);

            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(feedItemModel.getMain_image()).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                feedItemModel.setMainImageContainer(mIcon11);
            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try {
                mainImage.setImageBitmap(feedItemModel.getMainImageContainer());
            }
            catch(Exception e){
                e.printStackTrace();
            }
            title.setText(feedItemModel.getTitle());
            body.setText(feedItemModel.getBody());

            //set bundle data for readmore
            activityBundle.putString(getString(R.string.URL), feedItemModel.getUrl());

        }
    }


}
