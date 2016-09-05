package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtHomeData implements Serializable {
    private String id;
    private String thumbnail;
    private String image;
    private String name;
    private String address;
    private String foodName;
    private String foodPrice;
    private int grade;
    private int bookmarkCnt;
    private int reviewCnt;
    private String introduce;

    public String getImage() {
        return thumbnail;
    }

    public void setImage(String image) {
        this.thumbnail = image;
    }

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

    public String getFoodImageUrl() {
        return image;
    }

    public void setFoodImageUrl(String foodImageUrl) {
        this.image = foodImageUrl;
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

    public int getBookmarkCnt() {
        return bookmarkCnt;
    }

    public void setBookmarkCnt(int bookmarkCnt) {
        this.bookmarkCnt = bookmarkCnt;
    }

    public int getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(int reviewCnt) {
        this.reviewCnt = reviewCnt;
    }
}
