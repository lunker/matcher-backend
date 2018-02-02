package org.lunker.matcher.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dongqlee on 2018. 1. 9..
 */
@Entity
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String url;

    public ProjectEntity() {
    }

    public ProjectEntity(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("title: " + title);
        stringBuilder.append(", ");
        stringBuilder.append("url: " + url);
        return stringBuilder.toString();
    }
}
