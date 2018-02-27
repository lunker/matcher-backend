package org.lunker.matcher.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * Created by dongqlee on 2018. 2. 19..
 */
public class TimeZone {

    private LocalDateTime fromMatchingDate;
    private LocalDateTime toMatchingDate;

    public TimeZone() {
    }

    public TimeZone(LocalDateTime fromMatchingDate, LocalDateTime toMatchingDate) {
        this.fromMatchingDate = fromMatchingDate;
        this.toMatchingDate = toMatchingDate;
    }

    public LocalDateTime getFromMatchingDate() {
        return fromMatchingDate;
    }

    public void setFromMatchingDate(LocalDateTime fromMatchingDate) {
        this.fromMatchingDate = fromMatchingDate;
    }

    public LocalDateTime getToMatchingDate() {
        return toMatchingDate;
    }

    public void setToMatchingDate(LocalDateTime toMatchingDate) {
        this.toMatchingDate = toMatchingDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE).toString();
    }
}
