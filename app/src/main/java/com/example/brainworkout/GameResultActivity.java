package com.example.brainworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.brainworkout.databinding.ActivityGameResultBinding;
import com.example.brainworkout.databinding.ActivityMainBinding;

public class GameResultActivity extends AppCompatActivity {

    String finalScore;
    String maxScore;
    ActivityGameResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("finalScore")) {
            finalScore = String.valueOf(intent.getIntExtra("finalScore", 0));
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        maxScore = String.valueOf(preferences.getInt("maxScore", Integer.parseInt(finalScore)));
        binding.recordScoreTextView.setText(maxScore);
        binding.finalScoreTextView.setText(finalScore);
    }

    public void onCLickTryAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}