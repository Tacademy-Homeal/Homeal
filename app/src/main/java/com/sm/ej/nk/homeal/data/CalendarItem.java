package com.sm.ej.nk.homeal.data;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class CalendarItem implements Serializable{
    public int year;
    public int month;
    public int dayOfMonth;
    public int dayOfWeek;
    public boolean inMonth;
    public boolean isSelect;
    public boolean isLaunch;
    public boolean isMorning;
    public boolean isDinner;
    public String pax;
    public int sharing;
    public String id;

    public CalendarItem(){
        isSelect = false;
        isLaunch = false;
        isMorning = false;
        isDinner = false;
    }

    public StringBuilder getDate(int time){
        StringBuilder date = new StringBuilder();
        date.append(year)
                .append("/");
        if((month+1)<10){
            date.append("0")
                    .append(month+1);
        }else{
            date.append(month+1);
        }
        date.append("/");
        if(dayOfMonth<10){
            date.append("0")
                    .append(dayOfMonth);
        }else{
            date.append(dayOfMonth);
        }
        date.append(" ");

        switch(time){
            case 1:
                date.append("09:00:00");
                break;
            case 2:
                date.append("12:00:00");
                break;
            case 3:
                date.append("18:00:00");
                break;
        }
        Log.e("ssong Date", date.toString());
        return date;
    }

    public ArrayList items = new ArrayList();
}
