package com.example.booklookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class KeywordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);

        ArrayList<ABook> books = QueryUtils.extractBooks();

        BooksAdapter adapter = new BooksAdapter(this, books);

        ListView booksList = (ListView) findViewById(R.id.keywordlistview);

        booksList.setAdapter(adapter);
    }
}