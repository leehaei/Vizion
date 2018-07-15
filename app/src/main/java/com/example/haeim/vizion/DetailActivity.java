package com.example.haeim.vizion;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    DBHelper db;
    private String selectedName;
    private int selectedID;
    private ListView listView;

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new DBHelper(this);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id" , -1); //-1 default
        selectedName = receivedIntent.getStringExtra("name");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(selectedID, selectedName);
                finish();
            }
        });

       // populateDetails();
    }

    /*
    private void populateDetails(){
        Log.d(TAG, "populateDetails: Displaying data in ListView");

        // get all data into a list
        Cursor data = db.getData("name");
        ArrayList<String> listData = new ArrayList<>();
        listData.add("SELECT" + DBHelper.KEY_name + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_date + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_startingTime + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_location + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_meetingTime + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_meetingLocation + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_cost + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_bring + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_note + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");
        listData.add("SELECT" + DBHelper.KEY_organizer + " FROM " + DBHelper.TABLE + " WHERE "
                + DBHelper.KEY_ID + " = '" + selectedID + "'");

        //create the list adapter and set the adapter
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

    }
    */

}
