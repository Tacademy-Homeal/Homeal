package com.sm.ej.nk.homeal.data;

import com.sm.ej.nk.homeal.manager.CalendarManager;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class CalendarItemData implements CalendarManager.CalendarComparable<CalendarItemData>{
    public int year;
    public int month;
    public int day;
    public String text;

    public CalendarItemData(int year, int month, int day, String text){
        this.year = year;
        this.month = month;
        this.day = day;
        this.text = text;
    }

    @Override
    public int compareData(int year, int month, int day) {
        return ((this.year - year) * 12 + (this.month - month))*31 + this.day -day;
    }

    @Override
    public int compareToUsingCalendar(CalendarItemData another) {
        return ((this.year - another.year) * 12 + (this.month - another.month))*31 + this.day - another.day;
    }
}
