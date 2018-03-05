package org.lunker.matcher.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 16.
 */
public class MatchRequestBody {

    private String userId;
    private boolean isComplete=false;

    private int exerciseId;
    private int attendeeNum;

    private List<TimeZone> matchingDateCandidates;

    private List<Area> areaCandidates;

    public MatchRequestBody() {
    }

    public MatchRequestBody(String userId, boolean isComplete, int exerciseId, int attendeeNum, List<TimeZone> timeZonesCandidates, List<Area> areaCandidates) {
        this.userId = userId;
        this.isComplete = isComplete;
        this.exerciseId = exerciseId;
        this.attendeeNum = attendeeNum;
        this.matchingDateCandidates = timeZonesCandidates;
        this.areaCandidates = areaCandidates;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getAttendeeNum() {
        return attendeeNum;
    }

    public void setAttendeeNum(int attendeeNum) {
        this.attendeeNum = attendeeNum;
    }

    public List<TimeZone> getMatchingDateCandidates() {
        return matchingDateCandidates;
    }

    public void setMatchingDateCandidates(List<TimeZone> matchingDateCandidates) {
        this.matchingDateCandidates = matchingDateCandidates;
    }

    public List<Area> getAreaCandidates() {
        return areaCandidates;
    }

    public void setAreaCandidates(List<Area> areaCandidates) {
        this.areaCandidates = areaCandidates;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
    }
}
