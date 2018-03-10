package org.lunker.matcher.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

/**
 * Created by dongqlee on 2018. 2. 19..
 */
public class TimeZone {

    private LocalDate fromMatchingDate;
    private int fromMatchingHour;

    public TimeZone() {
    }

    public TimeZone(LocalDate fromMatchingDate, int fromMatchingHour) {
        this.fromMatchingDate = fromMatchingDate;
        this.fromMatchingHour = fromMatchingHour;
    }

    public LocalDate getFromMatchingDate() {
        return fromMatchingDate;
    }

    public void setFromMatchingDate(LocalDate fromMatchingDate) {
        this.fromMatchingDate = fromMatchingDate;
    }

    public int getFromMatchingHour() {
        return fromMatchingHour;
    }

    public void setFromMatchingHour(int fromMatchingHour) {
        this.fromMatchingHour = fromMatchingHour;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
    }
}
