package org.lunker.matcher.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by dongqlee on 2018. 3. 2..
 */
@Entity
@Table(indexes = {@Index(name = "IDX_MR_REQ", columnList = "req_id"), @Index(name = "IDX_MR_GU", columnList = "gu_id")})
public class MatchRequest {
    @Id
    @GeneratedValue
    @Column(name = "sub_req_id")
    private long id;

    // parent 'metarequest' id
    @Column(name = "req_id")
    private long reqId;

    @Column(name = "city_id")
    private int cityId;

    @Column(name = "gu_id")
    private int guId;

    private LocalDate fromDate;

    @Column(name = "start_hour")
    private int startHour;

//    private LocalDateTime toDate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name="modified_date")
    private Date modifiedDate;

    public MatchRequest() {}

    public MatchRequest(long reqId, int cityId, int guId, LocalDate fromDate, int startHour) {
        this.reqId = reqId;
        this.cityId = cityId;
        this.guId = guId;
        this.fromDate = fromDate;
        this.startHour = startHour;
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
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
