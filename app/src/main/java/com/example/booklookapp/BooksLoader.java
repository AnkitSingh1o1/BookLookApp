package com.example.booklookapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class BooksLoader extends AsyncTaskLoader<List<ABook>> {

    /** Query URL */
    private final String bUrl;

    public BooksLoader(Context context, String url) {
        super(context);
        bUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<ABook> loadInBackground() {
        if (bUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<ABook> books = QueryUtils.fetchEarthquakeData(bUrl);
        return books;
    }
}
