package com.example.booklookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------setting on click listener and calling intent to open respective classes--------------

        TextView comics = (TextView) findViewById(R.id.comics);
        comics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comicsIntent = new Intent(MainActivity.this,
                        KeywordActivity.class);
                //start the new Activity
                startActivity(comicsIntent);
            }
        });

        TextView computers = (TextView) findViewById(R.id.computer);
        computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent computersIntent = new Intent(MainActivity.this,
                        KeywordActivity.class);
                //start the new Activity
                startActivity(computersIntent);
            }
        });

        TextView cooking = (TextView) findViewById(R.id.cooking);
        cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cookingIntent = new Intent(MainActivity.this,
                        KeywordActivity.class);
                //start the new Activity
                startActivity(cookingIntent);
            }
        });

        TextView selfHelp = (TextView) findViewById(R.id.selfhelp);
        selfHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selfHelpIntent = new Intent(MainActivity.this,
                        KeywordActivity.class);
                //start the new Activity
                startActivity(selfHelpIntent);
            }
        });
//--------------------All listeners created---------------------------------------------------------
    }
}