package com.example.programmingpractisewk5b;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String filelink = "";
    private int drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SongCollection songCollection = new SongCollection();
    private SongCollection originalSongCollection = new SongCollection();

    ArrayList<Song> songlist = new ArrayList<Song>();
    List<Song> shuffleList = Arrays.asList(songCollection.songs);

    SeekBar seekBar;
    Handler handler = new Handler();
    Button repeatBtn;
    Button shuffleBtn;
    Boolean repeatFlag = false;
    Boolean shuffleFlag = false;

    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        //getting data from other activities
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        //Creating GSON Object
        Gson gson = new Gson();
        //Converting String Data into an arraylist
        TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
        songlist = gson.fromJson(songData.getString("songs"),token.getType());
        Log.d("temasek", "Retrieve position is" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()) {
                   player.seekTo(seekBar.getProgress());

                }

            }
        });

        repeatBtn = findViewById(R.id.repeatBtn);
        shuffleBtn = findViewById(R.id.shuffleBtn);
    }

    Runnable p_bar = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()) {
                seekBar.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }
    };

    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            gracefullyStopsWhenMusicEnds();

            btnPlayPause.setText("PAUSE");
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setText("PLAY");

        } else {
            player.start();
            seekBar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar,1000);
            btnPlayPause.setText("PAUSE");
        }
    }

    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (repeatFlag) {
                    playOrPauseMusic(null);

                }else {
                    //Toast.makeText(getBaseContext(), "The song had ended and the onCompleteListener is activated\n" +
                            //"The button text is changed to 'PLAY'", Toast.LENGTH_LONG).show();
                    btnPlayPause.setText("PLAY");

                }
            }
        });
    }

    public void displaySongBasedOnIndex(int currentIndex) {
        Song song = songCollection.getCurrentSong(currentIndex);
//        song = songlist.get(currentIndex);
        title = song.getTitle();
        artist = song.getArtist();
        filelink = song.getFileLink();
        drawable = song.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artist);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
    }

    public void playNext(View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking playNext,\nthe current index of this song\n" +
                "in the SongCollection array is now :" + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\n" +
                "in the SongCollection array is now :" + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now:" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);
    }

    public void repeatSong(View view) {

        if (repeatFlag) {
            repeatBtn.setBackgroundResource(R.drawable.repeat_off);

        } else {
            repeatBtn.setBackgroundResource(R.drawable.repeat_on);
        }
        repeatFlag = !repeatFlag;
    }
    public void shuffleSong(View view) {

        if (shuffleFlag) {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_off);
            songCollection = new SongCollection();

        } else {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_on);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffleFlag = !shuffleFlag;
    }
}