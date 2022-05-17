package com.example.brainworkout.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;


public class Generator {

    private static final Random generator = new Random();

    //Генерирует случайные значения в диапазоне
    public static int generateNumber(int rangeFromNull, boolean isOnlyPositive) {
        if (isOnlyPositive)
            return generator.nextInt(rangeFromNull + 1);
        else
            return generator.nextInt(rangeFromNull * 2 + 1) - rangeFromNull;
    }

    //Генерирует пример с ответами
    public static Exercise generateExercise() {
        int firstNumber = generateNumber(100, false);
        int secondNumber = generateNumber(100, false);
        int answer = firstNumber + secondNumber;
        int b = answer + generateNumber(7, false);
        int c = answer + generateNumber(7, false);
        int d = answer + generateNumber(7, false);
        char operator;

        if (secondNumber >= 0) {
            operator = '+';
        } else {
            operator = '-';
            secondNumber *= -1;
        }


        return new Exercise(String.format(Locale.getDefault(), "%d %c %d", firstNumber, operator, secondNumber), new ArrayList<>(Arrays.asList(answer, b, c, d)));
    }


}
