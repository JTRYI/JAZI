package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

public class MyFavouritesPlaylist extends AppCompatActivity {

    RecyclerView favList;
SongAdapter songAdapter;
SongCollection songCollection = new SongCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourites_playlist);
        favList = findViewById(R.id.recycleView);



        songAdapter = new SongAdapter(JustinBieber.favList);
        favList.setAdapter(songAdapter);
        favList.setLayoutManager(new LinearLayoutManager(this));
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });


        }
    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView){
        Log.d("temasek","my view id of the pressed image button is : " + myView.getId() );
        String resourceid = getResources(). getResourceName(myView.getId());
        Log.d("temasek","my resource id  of the pressed image button is : " + resourceid );
        resourceid = resourceid.substring(resourceid.indexOf("/") + 1);
        Log.d("temasek","The id of the pressed image button is : " + resourceid);
        int currentArrayIndex = songCollection.searchSongById(resourceid);
        Log.d("temasek" , "The index in the array for the song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }
}
