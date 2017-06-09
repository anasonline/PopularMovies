package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static final String POSTER_SIZE = "w185";
    private static final String BACKDROP_SIZE = "w780";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Find the views used to display movie information
        TextView mTitleTextView = (TextView) findViewById(R.id.tv_title);
        TextView mReleaseDateTextView = (TextView) findViewById(R.id.tv_release_date);
        TextView mPlotTextTextView = (TextView) findViewById(R.id.tv_plot_text);
        TextView mRatingTextView = (TextView) findViewById(R.id.tv_rating);
        ImageView mPosterImageView = (ImageView) findViewById(R.id.iv_poster);
        ImageView mBackdropImageView = (ImageView) findViewById(R.id.iv_backdrop);

        // Get the movie data from the intent (as a Parcelable object, add it into a bundle object,

        Movie movieData = getIntent().getExtras().getParcelable("EXTRA_MOVIE");

        if (movieData != null) {

            String title = movieData.getTitle();
            String releaseDate = movieData.getReleaseDate();
            String rating = movieData.getRating();
            String plot = movieData.getPlot();
            String backDropPath = movieData.getBackDropImageUrl();
            String posterPath = movieData.getPosterImageUrl();

            // Change the ActionBar's title to the movie title

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }

        // Add movie information into their respective views
        mTitleTextView.setText(title);
        mReleaseDateTextView.setText(releaseDate);
        mRatingTextView.setText(rating);
        mPlotTextTextView.setText(plot);

        // Get backdrop path and load the image with Picasso
        String fullBackdropPath = BASE_IMAGE_URL + BACKDROP_SIZE + backDropPath;
        Picasso.with(MovieDetail.this).load(fullBackdropPath).into(mBackdropImageView);

        // Get poster path and load the image with Picasso
        String fullPosterPath = BASE_IMAGE_URL + POSTER_SIZE + posterPath;
        Picasso.with(MovieDetail.this).load(fullPosterPath).into(mPosterImageView);
    }
    }
}