package com.sm.ej.nk.homeal.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class EtReserveData {
    private String fooname;
    private String reservedate;
    private String reserveperson;
    private String ckname;
    private String reservestate;
    private Drawable ckpicture;
    private String btnname;

    public String getBtnname() {
        return btnname;
    }

    public void setBtnname(String btnname) {
        this.btnname = btnname;
    }

    public Drawable getCkpicture() {
        return ckpicture;
    }

    public void setCkpicture(Drawable ckpicture) {
        this.ckpicture = ckpicture;
    }

    public EtReserveData() {
    }

    public EtReserveData(String fooname, String reservedate, String reserveperson, String ckname, String reservestate) {
        this.fooname = fooname;
        this.reservedate = reservedate;
        this.reserveperson = reserveperson;
        this.ckname = ckname;
        this.reservestate = reservestate;
    }

    public String getFooname() {
        return fooname;
    }

    public void setFooname(String fooname) {
        this.fooname = fooname;
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

    public String getCkname() {
        return ckname;
    }

    public void setCkname(String ckname) {
        this.ckname = ckname;
    }

    public String getReservestate() {
        return reservestate;
    }

    public void setReservestate(String reservestate) {
        this.reservestate = reservestate;
    }
}
