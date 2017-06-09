package com.example.android.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.List;

/**
 * Created by anas on 04.06.17.
 */

class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    // Query URL
    private final URL mUrl;

    public MovieLoader(Context context, URL url) {
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

        return QueryUtils.fetchMovieData(mUrl);
    }
}
