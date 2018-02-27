package org.lunker.matcher.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by dongqlee on 2018. 2. 17..
 */

@Entity
public class MatchRequestDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long requestId;

    private LocalDateTime fromDate;
    private Date toDate;

    private Date createdDate;
    private Date modifiedDate;
    @PrePersist
    public void createDate(){
        this.createdDate=this.modifiedDate=new Date();
    }


    public MatchRequestDate() {

    }

    public MatchRequestDate(long requestId, Date fromDate, Date toDate) {
        this.requestId = requestId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public MatchRequestDate(long requestId, Date fromDate, Date toDate, Date createdDate, Date modifiedDate) {
        this.requestId = requestId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


}
