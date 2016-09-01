package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class CkScheduleData {
    private String id;
    private String cooker_user_id;
    private String date;
    private String pax;
    private String sharing;

    public String getCooker_user_id() {
        return cooker_user_id;
    }

    public void setCooker_user_id(String cooker_user_id) {
        this.cooker_user_id = cooker_user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
