package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    TextView mTitle;
    ImageView mPoster;
    ImageView mBackdrop;
    TextView mReleaseDate;
    TextView mPlot;
    TextView mPlotText;
    TextView mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        mTitle = (TextView) findViewById(R.id.tv_title);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mPlot = (TextView) findViewById(R.id.tv_plot);
        mPlotText = (TextView) findViewById(R.id.tv_plot_text);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mPoster = (ImageView) findViewById(R.id.iv_poster);
        mBackdrop = (ImageView) findViewById(R.id.iv_backdrop);

        Intent intentThatStartedThisActivity = getIntent();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTitle.setText(extras.getString("title"));
            mReleaseDate.setText(extras.getString("release_date"));

            String backDropPath = extras.getString("backdrop_image");

            String fullImagePath = "http://image.tmdb.org/t/p/w780" + backDropPath;

            Picasso.with(MovieDetail.this).load(fullImagePath).into(mBackdrop);

            String posterPath = extras.getString("poster_image");

            String fullPosterPath = "http://image.tmdb.org/t/p/w185" + posterPath;

            Picasso.with(MovieDetail.this).load(fullPosterPath).into(mPoster);

            mRating.setText(extras.getString("rating"));
            mPlotText.setText(extras.getString("plot"));


        }


    }
}
