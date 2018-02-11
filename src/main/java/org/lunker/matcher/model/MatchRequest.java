package org.lunker.matcher.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.lunker.matcher.enums.City;
import org.lunker.matcher.enums.Gu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.regex.Matcher;

/**
 * Created by dongqlee on 2018. 2. 3..
 */

@Data
@Entity
@Getter
@Setter
public class MatchRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private City city;
    private Gu gu;

    private int excerciceType;

    //Ddate
    private int fromMatchingDate;
    private int toMatchingDate;
    private int attendNums;
    private Date createdDate;
    private Date modifiedDate;

    private boolean isMatched;

    public MatchRequest(){}

    public MatchRequest(City city, Gu gu, int excerciceType, int fromMatchingDate, int toMatchingDate, int attendNums, Date createdDate, Date modifiedDate, boolean isMatched) {
        this.city = city;
        this.gu = gu;
        this.excerciceType = excerciceType;
        this.fromMatchingDate = fromMatchingDate;
        this.toMatchingDate = toMatchingDate;
        this.attendNums = attendNums;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isMatched = isMatched;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Matching Request Info ::\n");
        stringBuilder.append("area : " + this.city + "\n");
        stringBuilder.append("type : " + this.excerciceType + "\n");
        stringBuilder.append("fromMatchingDate : " + this.fromMatchingDate+ "\n");
        stringBuilder.append("toMatchingDate : " + this.toMatchingDate+ "\n");

        return stringBuilder.toString();
    }
}
