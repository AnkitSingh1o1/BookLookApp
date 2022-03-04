package com.example.booklookapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static final String LOG_TAG =  QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link ABook} objects.
     */
    public static List<ABook> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<ABook> earthquakes = extractBooks(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }
    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }



    /**
     * Return a list of {@link ABook} objects that has been built up from
     * parsing a JSON response.
     */
    private static ArrayList<ABook> extractBooks(String jsonResponse) {
        String imageSecureLink = "https://books.google.com/books/content?id=";
        String imageSecureLink2 = "&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<ABook> booksArrayList = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJSONResponse = new JSONObject(jsonResponse);
            JSONArray itemsArray = baseJSONResponse.getJSONArray("items");







            for(int i = 0; i < itemsArray.length(); i++){
                JSONObject allObjects = itemsArray.getJSONObject(i);
                String id = allObjects.getString("id");
                //in volumeInfo
                JSONObject volumeInfo = allObjects.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                String infoLink;
                if(volumeInfo.has("infoLink")){
                infoLink = volumeInfo.getString("infoLink");
                }
                else{
                    infoLink = "https://play.google.com/store/books";
                }
                String publisher;
                if(volumeInfo.has("publisher")) {
                    publisher = volumeInfo.getString("publisher");
                }
                else{
                    publisher = "PUBLISHER_NOT_KNOWN";
                }
                String imageURL = imageSecureLink+id+imageSecureLink2+".jpg";
                StringBuilder authorName = new StringBuilder();
                if(volumeInfo.has("authors")) {
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    for (int j = 0; j < authors.length(); j++)
                        authorName.append(authors.getString(j));
                }
                else{
                    authorName = new StringBuilder("NOT_KNOWN");
                }
                String language;
                if(volumeInfo.has("language")) {
                    language = volumeInfo.getString("language");
                }
                else{
                    language = "LANGUAGE_NOT_KNOWN";
                }
                String pageCount;
                   if(volumeInfo.has("pageCount")) {
                       pageCount = volumeInfo.getString("pageCount");
                   }
                   else
                   {
                       pageCount = "NOT_KNOWN";
                   }

                //in salesInfo
                JSONObject salesInfo = allObjects.getJSONObject("saleInfo");
                String saleability = salesInfo.getString("saleability");

                ABook book = new ABook(infoLink,title, authorName.toString(),language,pageCount,saleability,publisher,imageURL);
                booksArrayList.add(book);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        // Return the list of earthquakes
        return booksArrayList;
    }

}