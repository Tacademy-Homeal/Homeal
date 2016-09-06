package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ZzimData implements Serializable {
    private String id;
    private String image;
    private String thumbnail;
    private String name;
    private String address;
    private String foodName;
    private String foodPrice;
    private int grade;
    private String bookmarkCnt;
    private String reviewCnt;
    private String introduce;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
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
}