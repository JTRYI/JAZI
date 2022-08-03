package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Gson gson = new Gson();
        String json = gson.toJson(favList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list",json);
        editor.apply();
        Log.d("gson", json);
        Toast.makeText(this, song.getTitle() + " has been added to the playlist. ", Toast.LENGTH_SHORT).show();

    }

    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView){
        String resourceid = getResources(). getResourceName(myView.getId());
        resourceid = resourceid.substring(resourceid.indexOf("/") + 1);
        Log.d("temasek","The id of the pressed image button is : " + resourceid);
        int currentArrayIndex = songCollection.searchSongById(resourceid);
        Log.d("temasek" , "The index in the array for the song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

}
