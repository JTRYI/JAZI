package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class JustinBieber extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    ArrayList<Song> favList = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justin_bieber);
    }

    public void addToFavourites(View view) {

        String songID = view.getContentDescription().toString();
        Song song = songCollection.searchSongById(songID);
        favList.add(song);
        //Toast.makeText(this,"button is clicked", Toast.LENGTH_SHORT).show();
    }

    public void gotoFavouriteActivity(View view) {

        for (int i = 0; i < favList.size() ; i++) {
            Log.d("temasek", favList.get(i).getTitle());
        }
    }
}