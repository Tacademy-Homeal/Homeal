package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class ReserveData {

    private int rpax; //reserve person number
    private int rstatus; //reserve stauts
    private int uid;// eater id
    private String sdate;//resserve date
    private String uname;//eater name
    private String uimage;//eater image
    private String mname;// memu name
    private int sid;// cooker id
    private int grade;
    private int rid; // reservation id

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRpax() {
        return this.rpax;
    }

    public void setRpax(int rpax) {
        this.rpax = rpax;
    }

    public int getRstatus() {
        return this.rstatus;
    }

    public void setRstatus(int rstatus) {
        this.rstatus = rstatus;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSdate() {
        return this.sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUimage() {
        return this.uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getMname() {
        return this.mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getSid() {
        return this.sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
