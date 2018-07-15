package com.example.haeim.vizion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Haeim on 2017-08-22.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    //table name
    public static final String TABLE = "Event";

    //table columns
    public static final String KEY_ID = "_id";
    public static final String KEY_name = "name";
    public static final String KEY_date = "date";
    public static final String KEY_startingTime = "starting_time";
    public static final String KEY_location = "location";
    public static final String KEY_meetingTime = "meeting_time";
    public static final String KEY_meetingLocation = "meeting_location";
    public static final String KEY_cost = "cost";
    public static final String KEY_bring = "what_to_bring";
    public static final String KEY_note = "note";
    public static final String KEY_organizer = "organizer";





    public DBHelper(Context context) {

        super(context, TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tables to create
        String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_name + " TEXT, "
                + KEY_date + " TEXT, "
                + KEY_startingTime + " TEXT, "
                + KEY_location + " TEXT, "
                + KEY_meetingTime + " TEXT, "
                + KEY_meetingLocation + " TEXT, "
                + KEY_cost + " TEXT, "
                + KEY_bring + " TEXT, "
                + KEY_note + " TEXT, "
                + KEY_organizer + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        //create tables again
        onCreate(db);
    }

    public boolean insert (String name, String date, String startTime, String location, String mTime, String mLocation, String cost, String bring, String note, String organizer) {
        SQLiteDatabase db = this.getWritableDatabase();
        //stores a set of values
        ContentValues values = new ContentValues();
        values.put(KEY_name, name);
        values.put(KEY_date, date);
        values.put(KEY_startingTime, startTime);
        values.put(KEY_location, location);
        values.put(KEY_meetingTime, mTime);
        values.put(KEY_meetingLocation, mLocation);
        values.put(KEY_cost, cost);
        values.put(KEY_bring, bring);
        values.put(KEY_note, note);
        values.put(KEY_organizer, organizer);

        Log.d(TAG, "insert: Adding " + name + " to " + TABLE);
        //inserting a row
        long result = db.insert(TABLE,null,values);
        //this.getWritableDatabase().insert(TABLE, null, values);
        //return true;

        //if inserted incorrectly
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public void delete (int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE + " WHERE " + KEY_ID + " = '" + id + "' AND "
                + KEY_name + " = '" + name + "'";
        Log.d(TAG, "delete: query: " + query);
        Log.d(TAG, "delete: Deleting " + name + " from database");
        db.execSQL(query);
    }


    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT" + KEY_ID + " FROM " + TABLE + " WHERE " + KEY_name +
                " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
