package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anas on 04.06.17.
 */

class Movie implements Parcelable {

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private final String mTitle;
    private final String mPosterImageUrl;
    private final String mBackdropImageUrl;
    private final String mReleaseDate;
    private final String mPlot;
    private final String mRating;

    public Movie(String title, String posterImageUrl, String backdropImageUrl, String releaseDate,
                 String plot, String rating) {
        mTitle = title;
        mPosterImageUrl = posterImageUrl;
        mBackdropImageUrl = backdropImageUrl;
        mReleaseDate = releaseDate;
        mPlot = plot;
        mRating = rating;
    }


    public Movie(Parcel in) {
        mTitle = in.readString();
        mPosterImageUrl = in.readString();
        mBackdropImageUrl = in.readString();
        mReleaseDate = in.readString();
        mPlot = in.readString();
        mRating = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mPosterImageUrl);
        parcel.writeString(mBackdropImageUrl);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPlot);
        parcel.writeString(mReleaseDate);
    }
}