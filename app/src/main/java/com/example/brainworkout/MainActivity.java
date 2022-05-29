package com.example.brainworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.example.brainworkout.Utils.Exercise;
import com.example.brainworkout.Utils.Generator;
import com.example.brainworkout.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CountDownTimer timer;
    int pointsCounter;
    String rightAnswer;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button1.setOnClickListener(onClickAnswerBtn(binding.button1));
        binding.button2.setOnClickListener(onClickAnswerBtn(binding.button2));
        binding.button3.setOnClickListener(onClickAnswerBtn(binding.button3));
        binding.button4.setOnClickListener(onClickAnswerBtn(binding.button4));

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        rightAnswer = createAndLoadExercise();

    }

    @Override
    protected void onStart() {
        super.onStart();
        pointsCounter = 0;
        binding.textViewPointCounter.setText("0");

        //Таймер на 60 секунд с реализацией анонимного класса
        timer = new CountDownTimer(12000, 1000){

            @Override
            public void onTick(long l) {
                int leftSeconds = (int)(l / 1000);
                binding.textViewTimer.setText(String.valueOf(leftSeconds));
            }

            @Override
            public void onFinish() {
                int maxScore = preferences.getInt(MAX_SCORE, 0);
                if (pointsCounter > maxScore) {
                    preferences.edit().putInt(MAX_SCORE, pointsCounter).apply();

                }
                Toast.makeText(MainActivity.this, "Время вышло",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GameResultActivity.class);
                intent.putExtra(FINAL_SCORE, pointsCounter);
                startActivity(intent);
            }
        };
        timer.start();
    }

    public OnClickListener onClickAnswerBtn(View view) {
        AppCompatButton btn = (AppCompatButton)view;

        if(btn.getText().toString().equals(rightAnswer)){
            binding.textViewPointCounter.setText(String.valueOf(++pointsCounter));
            timer.cancel();
            timer.start();
            rightAnswer = createAndLoadExercise();
        }
        else{
            if(pointsCounter > preferences.getInt(MAX_SCORE, 0)) {
                preferences.edit().putInt(MAX_SCORE, pointsCounter).apply();
            }
            timer.cancel();
            Intent intent = new Intent(MainActivity.this, GameResultActivity.class);
            intent.putExtra(FINAL_SCORE, String.valueOf(pointsCounter));
            startActivity(intent);
        }
        return null;
    }

    protected String createAndLoadExercise(){
        Exercise curExercise = Generator.generateExercise();
        String rightAnswer= String.valueOf(curExercise.getAnswers().get(0));
        ArrayList<Integer> shuffledAnswers = curExercise.getAnswers();
        Collections.shuffle(shuffledAnswers);
        binding.textViewExercise.setText(curExercise.getExercise());
        binding.button1.setText(String.valueOf(shuffledAnswers.remove(0)));
        binding.button2.setText(String.valueOf(shuffledAnswers.remove(0)));
        binding.button3.setText(String.valueOf(shuffledAnswers.remove(0)));
        binding.button4.setText(String.valueOf(shuffledAnswers.remove(0)));

        return rightAnswer;
    }

    protected static final String MAX_SCORE = "maxScore";
    protected static final String FINAL_SCORE = "maxScore";

}