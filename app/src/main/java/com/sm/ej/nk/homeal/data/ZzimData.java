package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class ZzimData implements Serializable {
    private String cid;
    private String image;
    private String thumbnail;
    private String name;
    private String address;
    private int accumulation;
    private int grade;
    private String bookmarkCnt;
    private String reviewCnt;
    private String introduce;
    private int isBookmark;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(int accumulation) {
        this.accumulation = accumulation;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getBookmarkCnt() {
        return bookmarkCnt;
    }

    public void setBookmarkCnt(String bookmarkCnt) {
        this.bookmarkCnt = bookmarkCnt;
    }

    public String getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(String reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getIsBookmark() {
        return isBookmark;
    }

    public void setIsBookmark(int isBookmark) {
        this.isBookmark = isBookmark;
    }
}