package com.v2v.melofymusicapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;
    private Context context;
    public MediaPlayer mediaPlayer;
    private int currentlyPlayingPosition = -1;

    public SongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.songTitle.setText(song.getTitle());

        // PLAY Button
        holder.btnPlay.setOnClickListener(v -> {
            Toast.makeText(context, "Playing: " + song.getTitle(), Toast.LENGTH_SHORT).show();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                notifyItemChanged(currentlyPlayingPosition);
            }

            mediaPlayer = MediaPlayer.create(context, song.getAudioResId());
            mediaPlayer.start();
            currentlyPlayingPosition = position;
            notifyDataSetChanged();

            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.release();
                mediaPlayer = null;
                int prevPosition = currentlyPlayingPosition;
                currentlyPlayingPosition = -1;
                notifyItemChanged(prevPosition);
            });
        });

        // PAUSE Button
        holder.btnPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        // STOP Button
        holder.btnStop.setOnClickListener(v -> {
            Toast.makeText(context, "music stopped", Toast.LENGTH_SHORT).show();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                notifyItemChanged(currentlyPlayingPosition);
                currentlyPlayingPosition = -1;
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle;
        ImageView btnPlay, btnPause, btnStop;

        public SongViewHolder(View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            btnPlay = itemView.findViewById(R.id.btnPlay);
            btnPause = itemView.findViewById(R.id.btnPause);
            btnStop = itemView.findViewById(R.id.btnStop);
        }
    }
}