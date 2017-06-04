package com.example.android.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anas on 04.06.17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Activity context, List<Movie> movies) {

        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        //movieImage.setImageResource(currentMovie.getImageUrl());

        TextView movieText = (TextView) gridItemView.findViewById(R.id.tv_title);
        movieText.setText(currentMovie.getTitle());

        return gridItemView;
    }

}
