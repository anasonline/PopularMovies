package com.example.android.popularmovies;

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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anas on 04.06.17.
 */

final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    // Private constructor - no one should create a QueryUtils object.
    private QueryUtils() {
    }

    public static List<Movie> fetchMovieData(URL requestUrl) {

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(requestUrl);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response, create a list of movies and return it

        return extractFeatureFromJson(jsonResponse);
    }

    // Make an HTTP request to the given URL and return a String as the response.
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Movie JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    // Convert the {@link InputStream} into a String which contains the
    // whole JSON response from the server.

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

    // Return a list of Moview objects that has been built up from parsing the given JSON response.

    private static List<Movie> extractFeatureFromJson(String movieJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding movies  to
        List<Movie> movies = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(movieJSON);

            //JSONObject response = baseJsonResponse.getJSONObject("response");

            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

            // For each moview in the resultsArray, create a Movie object
            for (int i = 0; i < resultsArray.length(); i++) {
                // Get a single moview item at position i within the list of movies

                JSONObject currentMovie = resultsArray.getJSONObject(i);

                // Extract the value of the key "poster_path"
                String posterImageUrl = currentMovie.getString("poster_path");

                // Extract the value of the key "backdrop_path"
                String backdropImageUrl = currentMovie.getString("backdrop_path");

                // Extract the value of the key "overview"
                String plot = currentMovie.getString("overview");

                // Extract the value of the key "release_date"
                String releaseDate = currentMovie.getString("release_date");

                // Extract the value of the key "title"
                String title = currentMovie.getString("title");

                // Extract the value of the key "vote_average"
                String rating = currentMovie.getString("vote_average");

                // Create a new movie object with the data extracted from JSON response
                Movie movie = new Movie(title, posterImageUrl, backdropImageUrl, releaseDate, plot, rating);

                // Add the new Movie to the list of movies
                movies.add(movie);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }
        // Return the list of movies
        return movies;
    }
}
