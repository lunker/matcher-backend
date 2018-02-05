package org.lunker.matcher.enums;

/**
 * Created by dongqlee on 2018. 2. 4..
 */
public enum Excercise {

    FOOTBALL("축구", 11),
    FUTSAL("풋살", 6);

    private String value;
    private int maxParticipants;


    Excercise(String value, int maxParticipants) {
        this.value = value;
        this.maxParticipants = maxParticipants;
    }
}
