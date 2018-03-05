package org.lunker.matcher.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
public class Area {

    private int cityId;
    private int guId;

    public Area() {
    }

    public Area(int cityId, int guId) {
        this.cityId = cityId;
        this.guId = guId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getGuId() {
        return guId;
    }

    public void setGuId(int guId) {
        this.guId = guId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
//        return ReflectionToStringBuilder.reflectionToString(this, new RecursiveToStringStyle()).toString();
    }
}
