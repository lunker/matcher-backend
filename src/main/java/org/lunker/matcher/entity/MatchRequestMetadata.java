package org.lunker.matcher.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.lunker.matcher.model.MatchRequestBody;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongqlee on 2018. 2. 16.
 */
@Entity
@Table(name = "match_request_meta", indexes = {@Index(name = "IDX_MRM", columnList = "is_complete, exercise_id")})
public class MatchRequestMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "req_id")
    private long id;

    private String userId;


    @Column(name = "is_complete")
    private boolean isComplete=false;

    @Column(name = "exercise_id")
    private int exerciseId;

    private int attendeeNum=1;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name="modified_date")
    private Date modifiedDate;

    @PrePersist
    public void createDate(){
        this.createdDate=this.modifiedDate=new Date();
    }

    public MatchRequestMetadata() {

    }

    public MatchRequestMetadata(String userId, boolean isComplete, int exerciseId, int attendeeNum, Date createdDate, Date modifiedDate) {
        this.userId = userId;
        this.isComplete = isComplete;
        this.exerciseId = exerciseId;
        this.attendeeNum = attendeeNum;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public static MatchRequestMetadata exchange(MatchRequestBody matchRequestBody){
        MatchRequestMetadata metadata=new MatchRequestMetadata();
        metadata.setAttendeeNum(matchRequestBody.getAttendeeNum());
        metadata.setExerciseId(matchRequestBody.getExerciseId());


        return metadata;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
