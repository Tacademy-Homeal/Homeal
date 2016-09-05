package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.viewholder.CkDetailHeaderViewHolder;
import com.sm.ej.nk.homeal.viewholder.CkDetailItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CkDetailHeaderViewHolder.OnCalendarHeaderViewClickListener{
    CkDetailData datas;
    CkDetailItemViewHolder menuholder;
    List<CkDetailMenuData> menuList;
    List<CkScheduleData> scheduleList;

    public CkDetailAdapter(){
    }


    public void addSchedule(List<CkScheduleData> data){
        scheduleList = data;
        notifyDataSetChanged();
    }

    public void addHeader(CkDetailData data){
        this.datas = data;
        notifyDataSetChanged();
    }

    private static final int HEADER_VIEW = 1;
    private static final int MENU_VIEW = 2;

    public void addMenuList(List<CkDetailMenuData> list){
        menuList = list;
        notifyDataSetChanged();
    }

    public void addMenu(CkDetailMenuData data){
        menuList = new ArrayList<>();
        menuList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0){
            return HEADER_VIEW;
        }else{
            return MENU_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case HEADER_VIEW: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_detail, parent, false);
                CkDetailHeaderViewHolder holder = new CkDetailHeaderViewHolder(parent.getContext(), view);
                holder.setSchedule(scheduleList);
                holder.setOnCalendarHeaderViewClickListener(this);
                return holder;
            }
            case MENU_VIEW:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ck_detail_menu, parent, false);
                return new CkDetailItemViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position ==0){
            CkDetailHeaderViewHolder headerholder = (CkDetailHeaderViewHolder)holder;
            headerholder.setData(datas);
        }else{
            menuholder = (CkDetailItemViewHolder)holder;
            menuholder.setData(menuList.get(position-1));
            if(position!=1){
                menuholder.day.setVisibility(View.INVISIBLE);
                menuholder.daynum.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        int size=0;
        if(!menuList.isEmpty()){
            size=menuList.size()+1;
        }
        return size;
    }

    @Override
    public void onCalendarHeaderViewClickListener(View view, CalendarItem data, int position) {
        if(listener!=null){
            listener.onDetailAdapterClick(view, data, position);
        }
    }

    public interface OnDetailAdapterClickListener{
        public void onDetailAdapterClick(View view, CalendarItem data, int position);
    }
    OnDetailAdapterClickListener listener;
    public void setOnDetailAdapterClickListener(OnDetailAdapterClickListener listener){
        this.listener = listener;
    }
}
