package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class ReviewData {
    private int eid;
    private String image;
    private String name;
    private String date;
    private String content;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview() {
        return content;
    }

    public void setReview(String review) {
        this.content = review;
    }
}
