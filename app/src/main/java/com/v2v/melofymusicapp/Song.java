package com.v2v.melofymusicapp;

public class Song {
    private String title;
    private int audioResId;

    public Song(String title, int audioResId) {
        this.title = title;
        this.audioResId = audioResId;
    }

    public String getTitle() {
        return title;
    }

    public int getAudioResId() {
        return audioResId;
    }
}
