package com.example.android.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by anas on 04.06.17.
 */

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    // Query URL
    private String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Movie> movies = QueryUtils.fetchMovieData(mUrl);
        return movies;
    }
}
