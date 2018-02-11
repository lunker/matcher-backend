package org.lunker.matcher.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 4..
 */


public class Exercise {

    public static int ID=0;
    public static final Exercise FOOTBALL=new Exercise("축구", 11);
    public static final Exercise FUTSAL=new Exercise("풋살", 6);


    private static List<Exercise> exercises =new ArrayList<>();

    static {
        exercises.add(FOOTBALL);
        exercises.add(FUTSAL);
    }

    public static List<Exercise> getExercise(){
        return exercises;
    }

    private int id;
    private String text;
    private String value;
    private int max;

    Exercise(String value, int max) {
        this.text = value;
        this.value = value;
        this.max = max;
        this.id= Exercise.ID++;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String serialize(){
        StringBuilder stringBuilder=new StringBuilder();

        stringBuilder.append("");

        return stringBuilder.toString();
    }
}
