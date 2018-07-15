package com.example.haeim.vizion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    DBHelper db;
    TextView nameText;
    TextView dateText;
    TextView startingTimeText;
    TextView locationText;
    TextView meetingTimeText;
    TextView meetingLocationText;
    TextView costText;
    TextView bringText;
    TextView noteText;
    TextView organizerText;
    Button addEntered;
    Button cancelEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        db = new DBHelper(this);

        nameText = (TextView) findViewById(R.id.nameText);
        dateText = (TextView) findViewById(R.id.dateText);
        startingTimeText = (TextView) findViewById(R.id.startingTimeText);
        locationText = (TextView) findViewById(R.id.locationText);
        meetingTimeText = (TextView) findViewById(R.id.meetingTimeText);
        meetingLocationText = (TextView) findViewById(R.id.meetingLocationText);
        costText = (TextView) findViewById(R.id.costText);
        bringText = (TextView) findViewById(R.id.bringText);
        noteText = (TextView) findViewById(R.id.noteText);
        organizerText = (TextView) findViewById(R.id.organizerText);

        Button addEntered = (Button) findViewById(R.id.addEntered);
        addEntered.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                toastMessage("Add entered!");
                addEntered(view);
            }
        });

        Button cancelEntered = (Button) findViewById(R.id.cancelEntered);
        cancelEntered.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                cancelEntered(view);
            }
        });

    }

    public void addEntered(View view){
        String name = nameText.getText().toString();
        String date = dateText.getText().toString();
        String startTime = startingTimeText.getText().toString();
        String location = locationText.getText().toString();
        String mTime = meetingTimeText.getText().toString();
        String mLocation = meetingLocationText.getText().toString();
        String cost = costText.getText().toString();
        String bring = bringText.getText().toString();
        String note = noteText.getText().toString();
        String organizer = organizerText.getText().toString();

        boolean result = db.insert(name, date, startTime, location, mTime, mLocation, cost, bring, note, organizer);

        if(result){
            toastMessage("Data Inserted Successfully");
            startActivity(new Intent(AddEventActivity.this, ListActivity.class));
        } else {
            toastMessage("Data Insert Unsuccessful");
        }
    }


    public void cancelEntered (View view) {
        toastMessage("Cancelled");
        startActivity(new Intent(AddEventActivity.this, ListActivity.class));
    }

    private void toastMessage(String message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}
