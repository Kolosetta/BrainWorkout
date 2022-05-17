package com.example.brainworkout.Utils;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    String exercise;
    ArrayList<Integer> answers;


    public Exercise(String exercise, ArrayList<Integer> answers) {
        this.exercise = exercise;
        this.answers = answers;
    }

    public String getExercise() {
        return exercise;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

}
