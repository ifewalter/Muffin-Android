package com.utiset.muffin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.utiset.muffin.models.FeedItemModel;

/**
 * Created by ife on 04/06/16.
 */
public class MuffinOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "muffin";

    protected static final String FEED_TABLE = "feeds";
    protected static final String BODY = "body";
    protected static final String TITLE = "title";
    protected static final String URL = "url";
    protected static final String MAIN_IMAGE = "main_image";
    protected static final String AUTHOR = "author";
    protected static final String CREATED = "created";
    protected static final String ID = "id";


    public MuffinOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final int DATABASE_VERSION = 1;

    private static final String FEED_TABLE_CREATE = "CREATE TABLE "+FEED_TABLE+" (" +
            BODY+" TEXT, " +
            TITLE +" TEXT, " +
            URL +" INTEGER, " +
            MAIN_IMAGE+" INTEGER," +
            AUTHOR+" TEXT, " +
            CREATED+ " TEXT, " +
            ID + " INTEGER not null unique," +
            "_id INTEGER PRIMARY KEY autoincrement);";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FEED_TABLE_CREATE);
    }


}
