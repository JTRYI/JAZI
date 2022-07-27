package com.example.programmingpractisewk5b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SongAdapter extends RecyclerView.Adapter<MyView> implements Filterable {
    List<Song> songs;
    List<Song> songsFiltered;
    Context context;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
        this.songsFiltered = songs;
    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song, parent, false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {


        // dk what is the error ask teacher
        Song song = songs.get(position);
        TextView artist = holder.titleArtist;
        artist.setText(song.getArtist());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        ImageView image = holder.image;
        image.setImageResource(song.getDrawable());
        holder.removeBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JustinBieber.favList.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songsFiltered.size();

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    songsFiltered = songs;
                } else {
                    List<Song> filteredList = new ArrayList<Song>();
                    for (int i = 0; i < songs.size(); i++) {
                        if (songs.get(i).getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(songs.get(i));
                            // Log to check what is in filteredList
                            Log.d("temasek", "Songs in list are" + songs.get(i));

                        }
                    }
                    songsFiltered = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = songsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                songsFiltered = (List<Song>) results.values;
                notifyDataSetChanged();


            }
        };
    }
}