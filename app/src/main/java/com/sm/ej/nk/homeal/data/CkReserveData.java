package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CkReserveData {
    private String foodName;
    private String reserveDate;
    private String reservePerson;
    private String etName;
    private int reserveState;
    private String etPictureUrl;
    private String btn_disagree;
    private String btn_agree;

    public String getBtn_agree() {
        return btn_agree;
    }

    public void setBtn_agree(String btn_agree) {
        this.btn_agree = btn_agree;
    }

    public String getBtn_disagree() {
        return btn_disagree;
    }

    public void setBtn_disagree(String btn_disagree) {
        this.btn_disagree = btn_disagree;
    }

    public String getEtName() {
        return etName;
    }

    public void setEtName(String etName) {
        this.etName = etName;
    }

    public String getEtPictureUrl() {
        return etPictureUrl;
    }

    public void setEtPictureUrl(String etPictureUrl) {
        this.etPictureUrl = etPictureUrl;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReservePerson() {
        return reservePerson;
    }

    public void setReservePerson(String reservePerson) {
        this.reservePerson = reservePerson;
    }

    public int getReserveState() {
        return reserveState;
    }

    public void setReserveState(int reserveState) {
        this.reserveState = reserveState;
    }
}
