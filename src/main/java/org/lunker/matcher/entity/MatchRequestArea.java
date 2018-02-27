package org.lunker.matcher.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongqlee on 2018. 2. 17..
 */

@Entity
public class MatchRequestArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long requestId;

    private int city_id;
    private int gu_id;

    private Date createdDate;

    private Date modifiedDate;

    @PrePersist
    public void createDate(){
        this.createdDate=this.modifiedDate=new Date();
    }

    public MatchRequestArea() {
    }

    public MatchRequestArea(long requestId, int city_id, int gu_id) {
        this.requestId = requestId;
        this.city_id = city_id;
        this.gu_id = gu_id;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getGu_id() {
        return gu_id;
    }

    public void setGu_id(int gu_id) {
        this.gu_id = gu_id;
    }
}
