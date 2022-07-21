package com.example.programmingpractisewk5b;

public class SongCollection {
    private static Song[] songs = new Song[9];

    public SongCollection() {

        Song STAY = new Song("S1001",
                "STAY",
                "Justin Bieber, The Kid LAROI",
                "https://p.scdn.co/mp3-preview/ac8171f8f8adc90bf412ede4cc9eaee2766915b2?cid=2afe87a64b0042dabf51f37318616965",
                2.36,
                R.drawable.the_kid_laroi_justin_bieber_stay);

        Song Sorry = new Song("S1002",
                "Sorry",
                "Justin Bieber",
                "https://drive.google.com/file/d/1OkQzc9eTIP6qBPUOp8twfL7ZkvZn3r5N/view?usp=sharing",
                3.25,
                R.drawable.sorry_justin_bieber);

        Song Baby = new Song("S1003",
                "Baby",
                "Justin Bieber ft Ludacris",
                "https://drive.google.com/file/d/1OkQzc9eTIP6qBPUOp8twfL7ZkvZn3r5N/view?usp=sharing",
                3.39,
                R.drawable.baby_justin_bieber);

        Song Photograph = new Song("S1004",
                "Photograph",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/34704823c55ae09f26988b106784f884bb781068?cid=2afe87a64b0042dabf51f37318616965",
                4.32,
                R.drawable.photograph);

        Song Perfect = new Song("S1005",
                "Perfect",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/229419b7fe43f4aa963e8ed8eecabc4b87c4958e?cid=2afe87a64b0042dabf51f37318616965",
                4.39,
                R.drawable.perfect_ed_sheeran);

        Song ShapeOfYou = new Song("S1006",
                "Shape of You",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/09e92af512355474ccf074988ea208ce6eb90a2b?cid=2afe87a64b0042dabf51f37318616965",
                3.9,
                R.drawable.shape_of_you_ed_sheeran);

        Song JustTheWayYouAre = new Song("S1007",
                "Just The Way You Are",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/bc7dc2784527bf13c72c4cd57071097aceb525c9?cid=2afe87a64b0042dabf51f37318616965",
                5.63,
                R.drawable.just_the_way_u_are_bruno_mars);

        Song TheLazySong = new Song("S1008",
                "The Lazy Song",
                "Bruno Mars",
                "https://drive.google.com/file/d/1dpUOTsXOxKgw1LplPEZGUkeyCoODL01X/view?usp=sharing",
                3.20,
                R.drawable.the_lazy_song_bruno_mars);

        Song ThatsWhatILike = new Song("S1009",
                "That's What I Like",
                "Bruno Mars",
                "https://p.scdn.co/mp3-preview/2d2501036399c05ca8dacb8f4fb5bb6174d0645a?cid=2afe87a64b0042dabf51f37318616965",
                3.44,
                R.drawable.thats_what_i_like_bruno_mars);

        songs[0] = STAY;
        songs[1] = Sorry;
        songs[2] = Baby;
        songs[3] = Photograph;
        songs[4] = Perfect;
        songs[5] = ShapeOfYou;
        songs[6] = JustTheWayYouAre;
        songs[7] = TheLazySong;
        songs[8] = ThatsWhatILike;

    }

    public static int searchSongById(String id) {
        for (int index = 0; index < songs.length; index++) {
            Song tempSong = songs[index];
            if (tempSong.getId().equals(id)) {
                return index;

            }
        }
        return -1;
    }

    public static Song returnSongById(int id){
        return songs[id];
    }

    public Song getCurrentSong(int currentSongId) {
        return songs[currentSongId];
    }

    public int getNextSong(int currentSongIndex) {
        if (currentSongIndex >= songs.length - 1) {
            return currentSongIndex;
        } else {
            return currentSongIndex + 1;
        }
    }

    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        }
        else {
            return currentSongIndex-1;
        }
    }
}