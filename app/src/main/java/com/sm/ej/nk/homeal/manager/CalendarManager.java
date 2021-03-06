package com.sm.ej.nk.homeal.manager;

import android.util.Log;

import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class CalendarManager {


    public interface  CalendarComparable<T>{
        public int compareData(int year, int month, int day);
        public int compareToUsingCalendar(T another);
    }

    public static class NoComparableObjectException extends Exception{
        public NoComparableObjectException(String detailMessage) {
            super(detailMessage);
        }
    }

    private static CalendarManager instance;
    private List<CalendarItem> item;

    private static Calendar mCalendar;

    private ArrayList mData = new ArrayList();

    public static CalendarManager getInstance(){
        if(instance == null){
            instance = new CalendarManager();
        }
        return instance;
    }
    public static void clearInstance(){
        instance = null;
    }

    private CalendarManager(){
        mCalendar = Calendar.getInstance();
    }

    public void setDataObject(ArrayList data) throws NoComparableObjectException {
        for(int i=0; i<data.size(); i++){
            Object o = data.get(i);
            if(!(o instanceof CalendarComparable)){
                throw  new NoComparableObjectException("Object not implements CalendarComparable");
            }
        }
        mData.clear();
        mData.addAll(data);
        Collections.sort(mData, new Comparator() {
            @Override
            public int compare(Object o, Object t1) {
                CalendarComparable clo = (CalendarComparable)o;
                CalendarComparable clt = (CalendarComparable)t1;

                return clo.compareToUsingCalendar(clt);
            }
        });
    }

    public CalendarData getCalendarData(int year, int month){
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return getCalendarData();
    }

    public CalendarData getLastMonthCalendarData(){
        mCalendar.add(Calendar.MONTH, -1);

        if(item == null){
            return getCalendarData();
        }else{
            return getSelectCalendarData(item);
        }
    }

    public CalendarData getNextMonthCalendarData(){
        mCalendar.add(Calendar.MONTH, 1);
        if(item == null){
            return getCalendarData();
        }else{
            return getSelectCalendarData(item);
        }
    }

    public CalendarData getCalendarData(){
        CalendarData data = new CalendarData();

        int currentMonthYear, currentMonth, lastMonthYear, lastMonth, nextMonthYear, nextMonth;

        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int datOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        int thisMonthLastDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        mCalendar.add(Calendar.MONTH, -1);
        int lastMonthLastDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        lastMonthYear = mCalendar.get(Calendar.YEAR);
        lastMonth = mCalendar.get(Calendar.MONTH);

        mCalendar.add(Calendar.MONTH, 2);
        nextMonthYear = mCalendar.get(Calendar.YEAR);
        nextMonth = mCalendar.get(Calendar.MONTH);

        mCalendar.add(Calendar.MONTH, -1);
        currentMonthYear = mCalendar.get(Calendar.YEAR);
        currentMonth = mCalendar.get(Calendar.MONTH);

        data.year = currentMonthYear;
        data.month = currentMonth;

        for(int i = Calendar.SUNDAY ; i<datOfWeek; i++){
            CalendarItem item = new CalendarItem();
            item.year = lastMonthYear;
            item.month = lastMonth;
            item.dayOfWeek = i;
            item.dayOfMonth = lastMonthLastDay-datOfWeek + i + 1;
            item.inMonth = false;
            data.days.add(item);
        }

        for(int i=0; i<thisMonthLastDay; i++){
            CalendarItem item = new CalendarItem();
            item.year = currentMonthYear;
            item.month = currentMonth;
            item.dayOfWeek = Calendar.SUNDAY + ((i + datOfWeek - Calendar.SUNDAY)% 7);
            item.dayOfMonth = i+1;
            item.inMonth = true;
            data.days.add(item);
        }

        int startNextWeek = Calendar.SUNDAY + ((datOfWeek - Calendar.SUNDAY + thisMonthLastDay)% 7);
        int count = (Calendar.SATURDAY +1 -startNextWeek) % 7;
        for(int i=0; i<count; i++){
            CalendarItem item = new CalendarItem();
            item.year = nextMonthYear;
            item.month = nextMonth;
            item.dayOfWeek = i + startNextWeek;
            item.dayOfMonth = i+1;
            item.inMonth = false;
            data.days.add(item);
        }

        for(int calendarImdex = 0, dataIndex = 0; calendarImdex < data.days.size() && dataIndex < mData.size();){
            CalendarComparable cc = (CalendarComparable)mData.get(dataIndex);
            CalendarItem item = data.days.get(calendarImdex);
            int compare = cc.compareData(item.year, item.month, item.dayOfMonth);
            if(compare <0){
                dataIndex++;
            }else if(compare >0){
                calendarImdex++;
            }else{
                item.items.add(cc);
                dataIndex++;
            }
        }
        return data;
    }

    public CalendarData getSelectCalendarData(List<CalendarItem> item){
        this.item = item;
        CalendarData data = getCalendarData();
        for(int i=0; i<data.days.size(); i++){
            data.days.get(i).idMap = new HashMap<>();
            for(int j=0; j<item.size(); j++){
                if(data.days.get(i).year == item.get(j).year && data.days.get(i).month == item.get(j).month && data.days.get(i).dayOfMonth == item.get(j).dayOfMonth){
                    if(item.get(j).isDinner){
                        data.days.get(i).isDinner = true;
                        data.days.get(i).idMap.put("Dinner", item.get(j).id);
                    }
                    if(item.get(j).isLaunch){
                        data.days.get(i).isLaunch = true;
                        data.days.get(i).idMap.put("Launch", item.get(j).id);
                    }

                    if(item.get(j).isMorning){
                        data.days.get(i).isMorning = true;
                        data.days.get(i).idMap.put("Morning", item.get(j).id);
                    }

                    Log.e("ssong", item.get(j).id+"dd");
                    data.days.get(i).isSelect = true;
                    data.days.get(i).sharing = item.get(j).sharing;
                    data.days.get(i).id = item.get(j).id;
                    data.days.get(i).pax = item.get(j).pax;
                }
            }
        }
        return data;
    }
}
