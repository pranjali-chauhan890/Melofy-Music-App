package com.v2v.melofymusicapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity {

    private RecyclerView songRecyclerView;
    private List<Song> songList;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        String category = getIntent().getStringExtra("category");

        TextView categoryTitle = findViewById(R.id.categoryTitle);
        categoryTitle.setText(category);

        songRecyclerView = findViewById(R.id.songRecyclerView);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadRomanticSongs();
    }

    private void loadRomanticSongs() {
        songList = new ArrayList<>();
        songList.add(new Song("Janam Janam", R.raw.janam_janam));
        songList.add(new Song("Akhiyaan Gulaab", R.raw.akhiyaan_gulaab));
        songList.add(new Song("Mujhko Barsaat Bana lo", R.raw.mujhko_barsaat_bana_lo));

        songAdapter = new SongAdapter(this, songList);
        songRecyclerView.setAdapter(songAdapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (songAdapter != null && songAdapter.mediaPlayer != null) {
            songAdapter.mediaPlayer.release();
            songAdapter.mediaPlayer = null;
        }
    }
}
