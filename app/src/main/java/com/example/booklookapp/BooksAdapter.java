package com.example.booklookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<ABook> {
    public BooksAdapter(Context context, List<ABook> book) {
        super(context, 0, book);
    }

    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null);
        }


        ABook currBook = getItem(position);

        ImageView bookThumbnail = (ImageView) convertView.findViewById(R.id.imageView2);
        try{
            URL imageURL = new URL(currBook.getThumbnail());
            Glide.with(getContext()).load(imageURL).into(bookThumbnail);}
        catch (IOException e){
            e.printStackTrace();
        }

        //Set Book Title
        TextView bookTitle = (TextView) convertView.findViewById(R.id.booktitle);
            bookTitle.setText(currBook.getTitle());

        //Set Book Authors
        TextView bookAuthor = (TextView) convertView.findViewById(R.id.authors);
        bookAuthor.setText(currBook.getAuthors());

        //Set Book language
        TextView bookLanguageAndPageCount = (TextView) convertView.findViewById(R.id.languageandpages);
        bookLanguageAndPageCount.setText(currBook.getLanguageAndPageCount());

        TextView bookPublisher = (TextView) convertView.findViewById(R.id.publisher);
        bookPublisher.setText(currBook.getPublisher());

        //Set Book Price and Currency Code
        TextView bookPrice = (TextView) convertView.findViewById(R.id.price);
        bookPrice.setText(currBook.getSaleability());

        //Send intent to open Complete info of the Book
        Button button = (Button) convertView.findViewById((R.id.button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context =view.getContext();
                Uri bookFullDetailUri = Uri.parse(currBook.getInfoLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, bookFullDetailUri);
                context.startActivity(intent);
            }
        });


        return convertView;
    }
}
