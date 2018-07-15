package com.example.haeim.vizion;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    DBHelper db;
    SQLiteDatabase newdb;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.Listview);
        db = new DBHelper(this);


        //creates a button to add events and redirects to addEventActivity page
        FloatingActionButton addEvent = (FloatingActionButton) findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, AddEventActivity.class));
            }
        });

        populateListView();
    }

    private void populateListView() {

        Log.d(TAG, "Displaying data");


        // get all data into a list
        ArrayList<String> listData = new ArrayList<>();
        newdb = db.getWritableDatabase();
        Cursor c = newdb.rawQuery("SELECT name FROM Event", null);
        c.moveToFirst();

        while (!(c.isAfterLast())) {
            if (c.getString(1) != null) {
                listData.add(c.getString(1));
            }
            c.moveToNext();
        }


        //create the list adapter and set the adapter
        if (listData == null) {
            listView.setAdapter(null);
        } else {
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //startActivity(new Intent(ListActivity.this, DetailActivity.class));
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = db.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is " + itemID);
                    Intent editScreenIntent = new Intent(ListActivity.this, DetailActivity.class);
                    editScreenIntent.putExtra("name", name);
                    //editScreenIntent.putExtra("id", itemID);
                    startActivity(editScreenIntent);
                } else {
                    toastMessage("Invalid");
                }

            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}