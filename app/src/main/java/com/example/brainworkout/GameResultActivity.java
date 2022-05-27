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
        setFinalAndMaxScore();
    }

    public void onCLickTryAgain(View view) {
        finish();
    }

    public void setFinalAndMaxScore() {
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(FINAL_SCORE)) {
            finalScore = String.valueOf(intent.getIntExtra(FINAL_SCORE, 0));
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        maxScore = String.valueOf(preferences.getInt(MAX_SCORE, Integer.parseInt(finalScore)));
        binding.recordScoreTextView.setText(maxScore);
        binding.finalScoreTextView.setText(finalScore);
    }

    protected static final String MAX_SCORE = "maxScore";
    protected static final String FINAL_SCORE = "maxScore";

}