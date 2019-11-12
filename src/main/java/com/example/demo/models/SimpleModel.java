package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import twitter4j.GeoLocation;

import java.util.Date;

@Document
public class SimpleModel {

    @Id
    private String id;
    private String user;
    private String text;
    private Date date;
    private GeoLocation location;
    private int favoriteCount;


    public SimpleModel() {}

    public SimpleModel(String user, String text, Date date, GeoLocation location, int favoriteCount) {
        this.user = user;
        this.text = text;
        this.date = date;
        this.location = location;
        this.favoriteCount = favoriteCount;
    }

    public String toString() {
        return "{\nid: " + this.id + ",\nScreen Name: " + this.user + ",\nText: " + this.text + ",\nDate: " + this.date + ",\nLocation: " + this.location + "\n}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) { this.text = text; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}
