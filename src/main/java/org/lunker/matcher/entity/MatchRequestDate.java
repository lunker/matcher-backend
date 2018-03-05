package org.lunker.matcher.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by dongqlee on 2018. 2. 17..
 */

@Entity
public class MatchRequestDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_date_id")
    private long id;

    @ManyToOne(targetEntity = MatchRequestMetadata.class)
    @JoinColumn(name = "req_id")
    private MatchRequestMetadata reqId;

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @PrePersist
    public void createDate(){
        this.createdDate=this.modifiedDate=LocalDateTime.now();
    }

    public MatchRequestDate() {

    }

    public MatchRequestDate(MatchRequestMetadata requestId, LocalDateTime fromDate, LocalDateTime toDate) {
        this.reqId = reqId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public MatchRequestDate(LocalDateTime fromDate, LocalDateTime toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public MatchRequestMetadata getReqId() {
        return reqId;
    }

    public void setReqId(MatchRequestMetadata reqId) {
        this.reqId = reqId;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
