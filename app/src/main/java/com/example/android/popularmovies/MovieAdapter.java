package com.example.android.popularmovies;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anas on 04.06.17.
 */

class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Activity context, List<Movie> movies) {

        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View gridItemView = convertView;

        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        // Find the movie at the given position in the movie grid
        Movie currentMovie = getItem(position);

        // Find the ImageView with the ID: movie_image

        ImageView movieImage = (ImageView) gridItemView.findViewById(R.id.movie_image);

        String posterPath = currentMovie != null ? currentMovie.getPosterImageUrl() : null;

        String fullImagePath = "http://image.tmdb.org/t/p/w342" + posterPath;

        Picasso.with(getContext()).load(fullImagePath).into(movieImage);

        Log.v("MovieAdapter", fullImagePath);

        return gridItemView;
    }

}
