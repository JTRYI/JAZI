package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Library extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


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