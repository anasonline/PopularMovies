package com.example.android.popularmovies;

/**
 * Created by anas on 04.06.17.
 */

public class Movie {

    private String mTitle;
    private String mPosterImageUrl;
    private String mBackdropImageUrl;
    private String mReleaseDate;
    private String mPlot;
    private String mRating;

    public Movie(String title, String posterImageUrl, String backdropImageUrl, String releaseDate,
                 String plot, String rating) {
        mTitle = title;
        mPosterImageUrl = posterImageUrl;
        mBackdropImageUrl = backdropImageUrl;
        mReleaseDate = releaseDate;
        mPlot = plot;
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterImageUrl() {
        return mPosterImageUrl;
    }

    public String getBackDropImageUrl() {
        return mBackdropImageUrl;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPlot() {
        return mPlot;
    }

    public String getRating() {
        return mRating;
    }
}
