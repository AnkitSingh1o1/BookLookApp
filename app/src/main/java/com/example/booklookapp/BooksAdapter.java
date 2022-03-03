package com.example.booklookapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<ABook> {
    public BooksAdapter(Context context, List<ABook> book) {
        super(context, 0, book);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        ABook currBook = getItem(position);

        //Set Book Title
        TextView bookTitle = (TextView) listItemView.findViewById(R.id.booktitle);
            bookTitle.setText(currBook.getTitle());

        //Set Book Authors
        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.authors);
        bookAuthor.setText(currBook.getAuthors());

        //Set Book language
        TextView bookLanguageAndPageCount = (TextView) listItemView.findViewById(R.id.languageandpages);
        bookLanguageAndPageCount.setText(currBook.getLanguageAndPageCount());

        //Set Book Publisher
        TextView bookPublisher = (TextView) listItemView.findViewById(R.id.publisher);
        bookPublisher.setText(currBook.getPublisher());

        //Set Book Price and Currency Code
        TextView bookPrice = (TextView) listItemView.findViewById(R.id.price);
        bookPrice.setText(currBook.getSaleability());


        return listItemView;
    }
}
