package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtHomeData implements Serializable {
    private String cid;
    private String thumbnail;
    private String image;
    private String name;
    private String address;
    private String foodName;
    private String foodPrice;
    private float grade;
    private int bookmarkCnt;
    private int reviewCnt;
    private int isBookmark;

    public int getIsBookmark() {
        return isBookmark;
    }

    public void setIsBookmark(int isBookmark) {
        this.isBookmark = isBookmark;
    }

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
        return cid;
    }

    public void setId(String id) {
        this.cid = id;
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

    public float getGrade() {
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
