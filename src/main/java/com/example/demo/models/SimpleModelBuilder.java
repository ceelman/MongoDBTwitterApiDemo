package com.example.demo.models;

import com.example.demo.models.SimpleModel;
import twitter4j.GeoLocation;
import java.util.Date;

public class SimpleModelBuilder {

    private String _id;
    private String user;
    private String text;
    private Date date;
    private GeoLocation location;
    private int favoriteCount;

    public SimpleModelBuilder() {}

    public SimpleModel build() {
        return new SimpleModel(this.user,
                this.text,
                this.date,
                this.location,
                this.favoriteCount);
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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
