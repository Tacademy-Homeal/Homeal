package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class CkScheduleData implements Serializable{
    private String date;
    private String pax;
    private int sharing;

    public CalendarItem getCalendar(){
        String[] calendar = date.split(" ");
        String[] calendar2 = calendar[0].split("/");
        CalendarItem item = new CalendarItem();
        item.year = Integer.parseInt(calendar2[0]);
        item.month = (Integer.parseInt(calendar2[1])-1);
        item.dayOfMonth = Integer.parseInt(calendar2[2]);
        String time = calendar[1].split(":")[0];
        item.isSelect = true;
        item.pax = this.pax;
        item.sharing = this.sharing;
        switch(time){
            case "09":
                item.isMorning = true;
                break;
            case "12":
                item.isLaunch = true;
                break;
            case "18":
                item.isDinner = true;
                break;
        }
        return item;
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

    public int getSharing() {
        return sharing;
    }

    public void setSharing(int sharing) {
        this.sharing = sharing;
    }

}
