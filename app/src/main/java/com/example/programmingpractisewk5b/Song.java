package com.example.programmingpractisewk5b;

public class Song {

    private String id;
    private String title;
    private String artist;
    private String fileLink;
    private double songLength;
    private int drawable;

    public Song (String id, String title, String artist, String fileLink, double songLength, int drawable){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.drawable = drawable;

    }

    public String getId(){ return this.id; }
    public String getTitle() { return this.title; }
    public String getArtist() { return this.artist; }
    public String getFileLink() { return this.fileLink; }
    public double getSongLength() { return this.songLength; }
    public int getDrawable() { return this.drawable; }


}
