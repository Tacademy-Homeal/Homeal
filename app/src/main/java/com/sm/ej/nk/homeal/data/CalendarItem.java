package com.sm.ej.nk.homeal.data;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class CalendarItem {
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
    public String sharing;
    public String id;

    public CalendarItem(){
        isSelect = false;
        isLaunch = false;
        isMorning = false;
        isDinner = false;
    }


    public ArrayList items = new ArrayList();
}
