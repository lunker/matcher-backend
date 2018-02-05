package org.lunker.matcher.model;

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

@Entity
public class MatchRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private City city;
    private Gu gu;
    private int dong;

    private int excerciceType;

    //Ddate
    private int fromMatchingDate;
    private int toMatchingDate;

//    private Date createdDate;


    public MatchRequest(){}



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
