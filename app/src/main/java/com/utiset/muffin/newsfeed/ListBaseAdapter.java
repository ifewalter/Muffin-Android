package com.utiset.muffin.newsfeed;

import android.app.Activity;
import android.app.admin.DeviceAdminInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.utiset.muffin.helpers.ImageHelper;
import com.utiset.muffin.models.FeedItemModel;
import com.utiset.muffin.muffin.FeedItemActivity;
import com.utiset.muffin.muffin.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by apple on 01/04/16.
 */
public class ListBaseAdapter extends BaseAdapter {


    Context context;

    ArrayList<FeedItemModel> feedItemModel;
    Typeface fonts1,fonts2;


    public ListBaseAdapter(Context context, ArrayList<FeedItemModel> feedItemModel) {


        this.context = context;
        this.feedItemModel = feedItemModel;
    }


    @Override
    public int getCount() {
        return feedItemModel.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItemModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final FeedItemModel feedItemModel = (FeedItemModel)getItem(position);
        fonts1 =  Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Light.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Regular.ttf");

        ViewHolder viewHolder = null;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list, null);

            viewHolder = new ViewHolder();

            viewHolder.newsimage1 = (ImageView)convertView.findViewById(R.id.newsimage1);

            viewHolder.title = (TextView)convertView.findViewById(R.id.newsname);

            viewHolder.title.setTypeface(fonts1);


            convertView.setTag(viewHolder);

            convertView.requestLayout();



        }else {

            viewHolder = (ViewHolder)convertView.getTag();

        }

        int adjustedHeight = new ImageHelper().calculateHeightFromAspectRatio(parent.getMeasuredWidth(), feedItemModel.getMainImageContainer());
        viewHolder.newsimage1.getLayoutParams().height = adjustedHeight;

        viewHolder.newsimage1.setImageBitmap(feedItemModel.mainImageContainer);

        viewHolder.newsimage1.requestLayout();

        convertView.requestLayout();

        viewHolder.title.setText(feedItemModel.getTitle());






        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), FeedItemActivity.class).putExtra("ID",feedItemModel.getId()));
            }
        });

        return convertView;
    }



    private class ViewHolder{

        ImageView newsimage1;
        TextView title;

    }


}

