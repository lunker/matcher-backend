package org.lunker.matcher.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by dongqlee on 2018. 3. 2..
 */
@Entity
public class MatchRequest {
    @Id
    @GeneratedValue
    @Column(name = "sub_req_id")
    private long id;

    private long reqId;

    @Column(name = "city_id")
    private int cityId;
    private int guId;

    private LocalDateTime fromDate;

    @Column(name = "start_hour")
    private int startHour;
    private LocalDateTime toDate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name="modified_date")
    private Date modifiedDate;

    public MatchRequest() {}

    public MatchRequest(long reqId, int cityId, int guId, LocalDateTime fromDate, LocalDateTime toDate) {
        this.reqId = reqId;
        this.cityId = cityId;
        this.guId = guId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public MatchRequest(long reqId, int cityId, int guId, LocalDateTime fromDate, int startHour, LocalDateTime toDate) {
        this.reqId = reqId;
        this.cityId = cityId;
        this.guId = guId;
        this.fromDate = fromDate;
        this.startHour = startHour;
        this.toDate = toDate;
    }

    @ManyToOne(targetEntity = MatchRequestMetadata.class)
    @JoinColumn(name = "req_id")
    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
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

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @PrePersist
    public void createDate(){
        this.createdDate=this.modifiedDate=new Date();
    }

}
