package com.example.programmingpractisewk5b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MyView> {
    List<Song> songs;
    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }
    Context context;

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song,parent,false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Song song = songs.get(position);
        TextView artist = holder.titleArtist;
        artist.setText(song.getArtist());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
