package com.sm.ej.nk.homeal.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarItem;

import java.util.Calendar;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class CalendarView extends RecyclerView.ViewHolder{
    public View view;
    public TextView textView;
    public ImageView imageView;
    CalendarItem item;
    Calendar calendar;

    public CalendarView(View view){
        super(view);
        this.view = view;
        textView = (TextView)view.findViewById(R.id.text_calendar_day);
        imageView = (ImageView)view.findViewById(R.id.image_calendar_check);
    }

    public void setData(CalendarItem item){
        this.item = item;
        if(!item.inMonth){
          textView.setVisibility(View.INVISIBLE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
        calendar = Calendar.getInstance();
        if(item.dayOfMonth==calendar.get(Calendar.DAY_OF_MONTH)){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }
        textView.setText(""+item.dayOfMonth);
        
    }

}
