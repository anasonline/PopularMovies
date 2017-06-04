package com.example.android.popularmovies;

/**
 * Created by anas on 04.06.17.
 */

public class Movie {

    private String mTitle;
    private String mImageUrl;
    private String mReleaseDate;
    private String mPlot;
    private String mRating;

    public Movie(String title, String imageUrl, String releaseDate, String plot, String rating) {
        mTitle = title;
        mImageUrl = imageUrl;
        mReleaseDate = releaseDate;
        mPlot = plot;
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
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
