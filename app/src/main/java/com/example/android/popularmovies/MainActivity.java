package com.example.android.popularmovies;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Movie>>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int MOVIE_LOADER_ID = 1; // Constant value for the Movie loader ID.

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";
    private static final String API_PARAM = "api_key";
    private static final String API_KEY = "***REMOVED***";
    private static final String SEPARATOR = "&";
    private static final String LANGUAGE = "en-US";

    private MovieAdapter mAdapter;
    private GridView mGridView;

    private String mSortBy;

    public static URL buildUrl(String filterBy) {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(filterBy)
                .appendQueryParameter(API_PARAM, API_KEY)
                .appendQueryParameter(SEPARATOR, LANGUAGE)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("MainActivity", builtUri.toString());

        return url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSortBy = POPULAR; //The default value is to sort movies by (popular)

        // Find a reference to the GridView in the layout
        mGridView = (GridView) findViewById(R.id.movies_grid);

        // Create a new adapter that takes an empty list of movies as input
        mAdapter = new MovieAdapter(MainActivity.this, new ArrayList<Movie>());

        // Set the adapter on the GridView
        mGridView.setAdapter(mAdapter);


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
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);

        } else {

            Log.v("MainActivity", "Loading Error");
        }

    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {

        // Create a new loader for the given URL
        return new MovieLoader(this, buildUrl(mSortBy));
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        // Clear the adapter of previous movie data
        mAdapter.clear();

        // If there is a valid list of movies, then add them to the adapter's
        // data set. This will trigger the GridView to update.
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();
        ActionBar actionBar = getSupportActionBar();
        switch (item.getItemId()) {
            case R.id.sort_by_top_rated:

                if (item.isChecked() == false) {
                    item.setChecked(true);
                }

                actionBar.setTitle("Top Rated");
                mSortBy = TOP_RATED;
                mAdapter.clear();
                getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
                return true;

            case R.id.sorty_by_popular:

                if (item.isChecked() == false) {
                    item.setChecked(true);
                }
                actionBar.setTitle("Popular");
                mSortBy = POPULAR;
                mAdapter.clear();
                getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.menu_popular)) ||
                key.equals(getString(R.string.menu_top_rated))) {
            // Clear the ListView as a new query will be kicked off
            mAdapter.clear();


            // Restart the loader to requery the USGS as the query settings have been updated
            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        }
    }
}

