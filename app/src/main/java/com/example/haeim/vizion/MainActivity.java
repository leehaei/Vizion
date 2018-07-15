package com.example.haeim.vizion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                pwEntered(view);
            }
        });
    }

    //Called when user enters a password
    public void pwEntered(View view) {
        String correctPW = "abc";

        EditText editText = (EditText) findViewById(R.id.editText);
        String password = editText.getText().toString();

        if(password.equals(correctPW)) {
            startActivity(new Intent(MainActivity.this, ListActivity.class));

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
                    //User clicked OK button
                }
            });
            builder.setMessage("Incorrect Password");

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        editText.setText("");
    }
}
