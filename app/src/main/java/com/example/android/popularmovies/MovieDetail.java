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
    TextView mTitleTextView;
    ImageView mPosterImageView;
    ImageView mBackdropImageView;
    TextView mReleaseDateTextView;
    TextView mPlotTextView;
    TextView mPlotTextTextView;
    TextView mRatingTextView;
    String mPosterPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Find the views used to display movie information
        mTitleTextView = (TextView) findViewById(R.id.tv_title);
        mReleaseDateTextView = (TextView) findViewById(R.id.tv_release_date);
        mPlotTextView = (TextView) findViewById(R.id.tv_plot);
        mPlotTextTextView = (TextView) findViewById(R.id.tv_plot_text);
        mRatingTextView = (TextView) findViewById(R.id.tv_rating);
        mPosterImageView = (ImageView) findViewById(R.id.iv_poster);
        mBackdropImageView = (ImageView) findViewById(R.id.iv_backdrop);

        // Get the movie data from the intent, add it into a bundle object,
        Bundle movieData = getIntent().getExtras();

        String title = movieData.getString("title");
        String releaseDate = movieData.getString("release_date");
        String rating = movieData.getString("rating");
        String plot = movieData.getString("plot");
        String backDropPath = movieData.getString("backdrop_image");
        String posterPath = movieData.getString("poster_image");

        // Change the ActionBar's title to the movie title
        getSupportActionBar().setTitle(title);

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