package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarItem;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ReserveRequestBodyViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView textDate;
    public EditText reservePaerson;

    public ReserveRequestBodyViewHolder(View view){
        super(view);
        this.view = view;
        textDate = (TextView)view.findViewById(R.id.text_item_reserve_request_date);
        reservePaerson = (EditText)view.findViewById(R.id.edit_reserve_request_reserveperson);
    }
    public void setCalendarData(CalendarItem data){
        textDate.setText(data.year+"년 "+(data.month+1)+"월 "+data.dayOfMonth + "일" );
    }
}
