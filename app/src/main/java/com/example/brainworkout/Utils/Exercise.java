package com.example.brainworkout.Utils;

import java.util.List;

public class Exercise {

    String exercise;
    int rightAnswer;
    List<Integer> wrongAnswers;


    public Exercise(String exercise, int rightAnswer, List<Integer> wrongAnswers) {
        this.exercise = exercise;
        this.rightAnswer = rightAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public String getExercise() {
        return exercise;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public List<Integer> getWrongAnswers() {
        return wrongAnswers;
    }
}
