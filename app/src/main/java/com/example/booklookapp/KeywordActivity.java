package com.example.booklookapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KeywordActivity extends AppCompatActivity implements LoaderCallbacks<List<ABook>> {

    public static String GOOGLE_BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
   String value = "";
    /** TextView that is displayed when the list is empty */
    private TextView bEmptyStateTextView;
    private BooksAdapter bAdapter;

    private static final int BOOK_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }

        //Set the Label of the Keyword Activity according to the searched Keyword
        setTitle(value.toUpperCase(Locale.ROOT)+" Books");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);


        ListView booksList = (ListView) findViewById(R.id.keywordlistview);
        bEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        booksList.setEmptyView(bEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        bAdapter = new BooksAdapter(this, new ArrayList<ABook>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        booksList.setAdapter(bAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            bEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<ABook>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new BooksLoader(this, GOOGLE_BOOKS_REQUEST_URL+value);
    }

    @Override
    public void onLoadFinished(Loader<List<ABook>> loader, List<ABook> books) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        bEmptyStateTextView.setText(R.string.no_books);

        // Clear the adapter of previous earthquake data
        //mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            //mAdapter.addAll(earthquakes);
            updateUi(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ABook>> loader) {
        // Loader reset, so we can clear out our existing data.
        bAdapter.clear();
    }
        /**
         * Update the screen to display information from the given {@link }.
         */
        private void updateUi(List books) {
            BooksAdapter adapter = new BooksAdapter(this, books);

            ListView booksList = (ListView) findViewById(R.id.keywordlistview);

            booksList.setAdapter(adapter);
        }


}

