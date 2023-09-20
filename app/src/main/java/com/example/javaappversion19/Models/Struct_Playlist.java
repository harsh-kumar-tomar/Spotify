package com.example.javaappversion19.Models;

public class Struct_Playlist {

    private int ImageID ;
    private String PlaylistName;
    private String NoOfSongs;

    public Struct_Playlist(int imageID, String playlistName, String noOfSongs) {
        ImageID = imageID;
        PlaylistName = playlistName;
        NoOfSongs = noOfSongs;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public String getPlaylistName() {
        return PlaylistName;
    }

    public void setPlaylistName(String playlistName) {
        PlaylistName = playlistName;
    }

    public String getNoOfSongs() {
        return NoOfSongs;
    }

    public void setNoOfSongs(String noOfSongs) {
        NoOfSongs = noOfSongs;
    }
}
