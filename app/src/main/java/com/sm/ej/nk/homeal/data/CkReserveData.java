package com.sm.ej.nk.homeal.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CkReserveData {
    private String foodname;
    private String reservedate;
    private String reserveperson;
    private String etname;
    private String reservestate;
    private Drawable etpicture;
    private String btndisagree;
    private String btnagree;

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getReservedate() {
        return reservedate;
    }

    public void setReservedate(String reservedate) {
        this.reservedate = reservedate;
    }

    public String getReserveperson() {
        return reserveperson;
    }

    public void setReserveperson(String reserveperson) {
        this.reserveperson = reserveperson;
    }

    public String getEtname() {
        return etname;
    }

    public void setEtname(String etname) {
        this.etname = etname;
    }

    public String getReservestate() {
        return reservestate;
    }

    public void setReservestate(String reservestate) {
        this.reservestate = reservestate;
    }

    public Drawable getEtpicture() {
        return etpicture;
    }

    public void setEtpicture(Drawable etpicture) {
        this.etpicture = etpicture;
    }

    public String getBtndisagree() {
        return btndisagree;
    }

    public void setBtndisagree(String btndisagree) {
        this.btndisagree = btndisagree;
    }

    public String getBtnagree() {
        return btnagree;
    }

    public void setBtnagree(String btnagree) {
        this.btnagree = btnagree;
    }
}
