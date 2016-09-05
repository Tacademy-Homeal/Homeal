package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class CkScheduleData implements Serializable{
    private String id;
    private String cooker_user_id;
    private String date;
    private String pax;
    private String sharing;

    public CalendarItem getCalendar(){
        String[] calendar = date.split("T");
        String[] calendar2 = calendar[0].split("-");
        CalendarItem item = new CalendarItem();
        item.year = Integer.parseInt(calendar2[0]);
        item.dayOfMonth = Integer.parseInt(calendar2[1]);
        item.month = Integer.parseInt(calendar2[2]);
        String time = calendar[1].split(":")[0];
        item.isSelect = true;
        item.id = this.id;
        item.pax = this.pax;
        item.sharing = this.sharing;
        switch(time){
            case "09":
                item.isMorning = true;
                break;
            case "12":
                item.isLaunch = true;
                break;
            case "06":
                item.isDinner = true;
                break;
        }
        return item;
    }

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
