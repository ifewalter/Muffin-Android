package com.utiset.muffin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.utiset.muffin.models.FeedItemModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ife on 05/06/16.
 */
public class FeedData extends MuffinOpenHelper {
    public FeedData(Context context) {
        super(context);
    }

    public void insertFeed(FeedItemModel model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BODY, model.getBody());
        contentValues.put(TITLE, model.getTitle());
        contentValues.put(CREATED, model.getCreated());
        contentValues.put(URL, model.getUrl());
        contentValues.put(MAIN_IMAGE, model.getMain_image());
        contentValues.put(AUTHOR, model.getAuthor());
        contentValues.put(ID, model.getId());

        db.replace(FEED_TABLE, null, contentValues);
    }

    public ArrayList<FeedItemModel> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + FEED_TABLE, null);
        ArrayList<FeedItemModel> feedItemModels = new ArrayList<FeedItemModel>();

        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                feedItemModels.add(
                        new FeedItemModel(res.getString(res.getColumnIndex(BODY)),
                                res.getString(res.getColumnIndex(TITLE)),
                                res.getString(res.getColumnIndex(AUTHOR)),
                                res.getString(res.getColumnIndex(CREATED)),
                                res.getString(res.getColumnIndex(URL)),
                                res.getString(res.getColumnIndex(MAIN_IMAGE)),
                                Integer.parseInt(res.getString(res.getColumnIndex(ID)))));
                res.moveToNext();
            }
        }


        return feedItemModels;
    }

    public FeedItemModel getData(Integer id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + FEED_TABLE + " where " + ID + " = " + String.valueOf(id), null);
        FeedItemModel feedItemModel = null;
        if (res.getCount() == 1)
        {
            res.moveToFirst();
           feedItemModel = new FeedItemModel(res.getString(res.getColumnIndex(BODY)),
                    res.getString(res.getColumnIndex(TITLE)),
                    res.getString(res.getColumnIndex(AUTHOR)),
                    res.getString(res.getColumnIndex(CREATED)),
                    res.getString(res.getColumnIndex(URL)),
                    res.getString(res.getColumnIndex(MAIN_IMAGE)),
                    Integer.parseInt(res.getString(res.getColumnIndex(ID))));
        }
        return feedItemModel;
    }
}
