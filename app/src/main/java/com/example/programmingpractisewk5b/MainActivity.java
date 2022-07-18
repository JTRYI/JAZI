package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder (R.id.firstFragment,R.id.secondFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


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