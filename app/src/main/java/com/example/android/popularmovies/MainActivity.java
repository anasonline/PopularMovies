package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter mAdapter;
    private GridView mGridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the GridView in the layout
        mGridView = (GridView) findViewById(R.id.movies_grid);

        // Create a new adapter that takes an empty list of movies as input
        mAdapter = new MovieAdapter(MainActivity.this, new ArrayList<Movie>());

        // Set the adapter on the GridView
        mGridView.setAdapter(mAdapter);
    }


}

