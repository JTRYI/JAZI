package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MyFavouritesPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourites_playlist);

        for (int i = 0; i < JustinBieber.favList.size(); i++) {
            Log.d("temasek", JustinBieber.favList.get(i).getTitle());
        }
    }
}