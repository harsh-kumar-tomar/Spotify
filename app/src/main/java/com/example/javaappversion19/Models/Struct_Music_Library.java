package com.example.javaappversion19.Models;

public class Struct_Music_Library {

    private String SongName;
    private String url;
    private String imgUrl;
    private String singer ;

    public Struct_Music_Library(String songName, String url, String imgUrl , String singer) {
        SongName = songName;
        this.url = url;
        this.imgUrl = imgUrl;
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
