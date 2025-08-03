package com.v2v.melofymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Click listeners for each card
    public void openLofiCategory(View view) {
        openCategory("Lofi Beats");
    }

    public void openRomanticCategory(View view) {
        openCategory("Romantic");
    }

    public void openWorkoutCategory(View view) {
        openCategory("Workout");
    }

    public void openInstrumentalCategory(View view) {
        openCategory("Instrumental");
    }

    public void openHipHopCategory(View view) {
        openCategory("Hip Hop");
    }

    public void openChillCategory(View view) {
        openCategory("Chill Vibes");
    }

    private void openCategory(String categoryName) {
        Intent intent = new Intent(this, SongListActivity.class);
        intent.putExtra("category", categoryName);
        startActivity(intent);
    }
}