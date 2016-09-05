package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

public class CkreserveData implements Serializable {
    private int rpax;
    private int rstatus;
    private int uid;
    private String sdate;
    private String uname;
    private String uimage;
    private String mname;
    private int sid;

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
