package org.lunker.matcher.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongqlee on 2018. 2. 17..
 */

@Entity
public class MatchRequestArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_area_id")
    private long id;

    @ManyToOne(targetEntity = MatchRequestMetadata.class)
    @JoinColumn(name = "req_id")
    private MatchRequestMetadata reqId;

    private int cityId;
    private int guId;

    private Date createdDate;

    private Date modifiedDate;

    @PrePersist
    public void createDate(){
        this.createdDate=this.modifiedDate=new Date();
    }

    public MatchRequestArea() {
    }

    public MatchRequestArea(MatchRequestMetadata reqId, int cityId, int guId) {
        this.reqId = reqId;
        this.cityId = cityId;
        this.guId = guId;
    }

    public MatchRequestMetadata getReqId() {
        return reqId;
    }

    public void setReqId(MatchRequestMetadata reqId) {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
    }
}
