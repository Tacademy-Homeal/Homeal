package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.viewholder.CkHomeItemViewHolder;
import com.sm.ej.nk.homeal.viewholder.ReserveRequestBodyViewHolder;

import java.util.List;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ReserveRequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CkDetailMenuData> menuDatas;
    CalendarItem calendarItem;
    CkHomeItemViewHolder itemViewHolder;
    ReserveRequestBodyViewHolder headeritemViewHolder;

    private static final int BODY_VIEW=0;
    private static final int MENU_VIEW = 1;

    public void setMenu(List<CkDetailMenuData> datas){
        this.menuDatas = datas;
    }
    public void setCalendar(CalendarItem calendar){
        this.calendarItem = calendar;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==menuDatas.size()){
            return BODY_VIEW;
        }else{
            return MENU_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case MENU_VIEW:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_home_item, parent, false);
                return new CkHomeItemViewHolder(view, parent.getContext());
            }
            case BODY_VIEW:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_reserve_request_body, parent, false);
                return new ReserveRequestBodyViewHolder(view);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position==menuDatas.size()){
            headeritemViewHolder = (ReserveRequestBodyViewHolder)holder;
            headeritemViewHolder.setCalendarData(calendarItem);
        }else{
            itemViewHolder = (CkHomeItemViewHolder) holder;
            itemViewHolder.setData(menuDatas.get(position));
        }
    }
    public String time;
    public String getEidtText(){
        return headeritemViewHolder.reservePaerson.getText().toString();
    }
    public String getGroup(){
        switch (headeritemViewHolder.group.getCheckedRadioButtonId()){
            case R.id.radio_morning:
                time = "Morning";
                break;
            case R.id.radio_launch:
                time = "Launch";
                break;
            case R.id.radio_dinner:
                time = "Dinner";
                break;
        }
        return time;
    }
    public boolean checkValue(){
        if(TextUtils.isEmpty(headeritemViewHolder.reservePaerson.getText().toString())){
            return false;
        }

        if(headeritemViewHolder.group.getCheckedRadioButtonId()==-1){
            return false;
        }
        return true;
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(!menuDatas.isEmpty()){
            size=menuDatas.size()+1;
        }
        return size;
    }
}
