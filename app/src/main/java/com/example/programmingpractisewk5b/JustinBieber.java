package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class JustinBieber extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    static ArrayList<Song> favList = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justin_bieber);
    }

    public void addToFavourites(View view) {

        String songID = view.getContentDescription().toString();
        int selectedId = songCollection.searchSongById(songID);
        Song song= songCollection.returnSongById(selectedId);
        favList.add(song);
        //Toast.makeText(this,"button is clicked", Toast.LENGTH_SHORT).show();

    }

    public void gotoFavouriteActivity(View view) {

        Intent intent = new Intent(this,MyFavouritesPlaylist.class);
        startActivity(intent);

        }
    }
