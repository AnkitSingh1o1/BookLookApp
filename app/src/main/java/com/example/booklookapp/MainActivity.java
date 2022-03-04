package com.example.booklookapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------setting on click listener and calling intent to open respective classes--------------


        Button button = findViewById(R.id.searchbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameField = (EditText) findViewById(R.id.searchbar);
                Editable nameEditable = nameField.getText();
                String name = nameEditable.toString();

                Intent i = new Intent(MainActivity.this, KeywordActivity.class);
                i.putExtra("key", name);
                startActivity(i);
            }
        });



        TextView comics = (TextView) findViewById(R.id.comics);
        comics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent comicsIntent = new Intent(MainActivity.this,
                            KeywordActivity.class);
                    //start the new Activity
                    startActivity(comicsIntent);
                }
                catch (UserManager.UserOperationException ex){

                }
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