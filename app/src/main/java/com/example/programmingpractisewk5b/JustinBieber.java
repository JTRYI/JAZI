package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class JustinBieber extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    static ArrayList<Song> favList = new ArrayList<Song>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justin_bieber);
        // name "playList" is stored in phone
        sharedPreferences = getSharedPreferences("playList", MODE_PRIVATE);
        String albums = sharedPreferences.getString("list","");
        if (!albums.equals("")) {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
            Gson gson = new Gson();
            favList = gson.fromJson(albums,token.getType());

        }
    }

    public void addToFavourites(View view) {

        String songID = view.getContentDescription().toString();
        int selectedId = songCollection.searchSongById(songID);
        Song song = songCollection.returnSongById(selectedId);
        favList.add(song);
        //for persistent playlist
        Gson gson = new Gson();
        // use gson to convert whatever is inside favList to a string
        //json contains string representation for favList
        String json = gson.toJson(favList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list",json);
        editor.apply();
        Log.d("gson", json);
        Toast.makeText(this, song.getTitle() + " has been added to the playlist. ", Toast.LENGTH_SHORT).show();

    }


}
