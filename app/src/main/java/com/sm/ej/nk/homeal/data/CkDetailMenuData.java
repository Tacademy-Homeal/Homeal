package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailMenuData implements Serializable{
    public String image;
    public String name;
    public String foodAddress;
    public String foodTime;
    public boolean isSoldOut;
    public String id;
    public String numday;
    public String day;
    public int activation;
    public int currency;
    public int price;
    public String introduce;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodName() {
        return name;
    }

    public void setFoodName(String foodName) {
        this.name = foodName;
    }

    public String getFoodAddress() {
        return foodAddress;
    }

    public void setFoodAddress(String foodAddress) {
        this.foodAddress = foodAddress;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}
