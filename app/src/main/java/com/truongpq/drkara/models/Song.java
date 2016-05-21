package com.truongpq.drkara.models;

/**
 * Created by TruongPQ on 5/16/16.
 */
public class Song {
    private String id;
    private String songID;
    private String name;
    private String nameClean;
    private String lyric;
    private String lyricClean;
    private String musician;
    private String musicanClean;

    public Song() {}

    public Song(String id, String songID, String name, String lyric, String musician, String nameClean, String lyricClean, String musicanClean) {
        this.id = id;
        this.songID = songID;
        this.name = name;
        this.lyric = lyric;
        this.musician = musician;
        this.nameClean = nameClean;
        this.lyricClean = lyricClean;
        this.musicanClean = musicanClean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getNameClean() {
        return nameClean;
    }

    public void setNameClean(String nameClean) {
        this.nameClean = nameClean;
    }

    public String getLyricClean() {
        return lyricClean;
    }

    public void setLyricClean(String lyricClean) {
        this.lyricClean = lyricClean;
    }

    public String getMusicanClean() {
        return musicanClean;
    }

    public void setMusicanClean(String musicanClean) {
        this.musicanClean = musicanClean;
    }
}
