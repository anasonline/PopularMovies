package com.example.android.popularmovies;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Movie>> {

    private static final int MOVIE_LOADER_ID = 1; // Constant value for the NewsItem loader ID.
    private static final String REQUEST_URL = "https://api.themoviedb.org/3/movie/popular?api_key=***REMOVED***&language=en-US&page=1";
    private MovieAdapter mAdapter;
    private GridView mGridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        return new MovieLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        // Clear the adapter of previous news data
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
}

