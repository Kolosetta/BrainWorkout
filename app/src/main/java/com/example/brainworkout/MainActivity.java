package com.example.brainworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.brainworkout.Utils.Generator;
import com.example.brainworkout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.textViewExercise.setText(Generator.generateExercise().getExercise());


        //Таймер на 60 секунд с реализацией анонимного класса
        CountDownTimer timer = new CountDownTimer(5000, 1000){

            @Override
            public void onTick(long l) {
                int leftSeconds = (int)(l / 1000);
                binding.textViewTimer.setText(String.valueOf(leftSeconds));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Отчет окончен",Toast.LENGTH_LONG).show();
            }
        };


        timer.start();


        /*
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("test", "MyValue").apply();
        String test = preferences.getString("test", null);

        Toast.makeText(this, test,Toast.LENGTH_LONG).show();*/
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}