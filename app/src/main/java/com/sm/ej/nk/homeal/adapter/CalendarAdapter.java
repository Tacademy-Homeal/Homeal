package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.view.CalendarView;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarView> {
   Context context;
    CalendarData data;

    public CalendarAdapter(Context context, CalendarData data){
        this.context = context;
        this.data = data;
    }

    @Override
    public CalendarView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ck_detail_calender, parent, false);
        return new CalendarView(view);
    }

    @Override
    public void onBindViewHolder(CalendarView holder, int position) {
        holder.setData(data.days.get(position));
    }

    @Override
    public int getItemCount() {
        int size=0;
        if(data!=null){
            size = data.days.size();
        }
        return size;
    }


}
