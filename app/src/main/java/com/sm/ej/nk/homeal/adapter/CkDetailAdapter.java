package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.util.Attributes;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkInfoResult;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.viewholder.CkDetailHeaderViewHolder;
import com.sm.ej.nk.homeal.viewholder.CkDetailItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder> implements CkDetailHeaderViewHolder.OnCalendarHeaderViewClickListener, CkDetailItemViewHolder.OnMenuSwipeClickLIstener, CkDetailHeaderViewHolder.OnMapClickListener{
    CkDetailData datas;
    CkDetailItemViewHolder menuholder;
    CkDetailHeaderViewHolder headerholder;
    List<CkDetailMenuData> menuList;
    List<CkScheduleData> scheduleList;
    List<ThumbnailsData> thumbnailsDatas;
    CalendarItem calendarItem;

    public CkDetailAdapter(){
    }

    public void setResult(CkInfoResult result){
        this.menuList = result.getCooker_menu();
        this.scheduleList = result.getCooker_schedule();
        this.thumbnailsDatas = result.getCooker_thumbnail();
        this.datas = result.getCooker_info();
        notifyDataSetChanged();
    }

    public void addThumbnails(List<ThumbnailsData> data){
        this.thumbnailsDatas = data;
        notifyDataSetChanged();
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
        switch (viewType) {
            case HEADER_VIEW: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_detail, parent, false);
                CkDetailHeaderViewHolder holder = new CkDetailHeaderViewHolder(parent.getContext(), view);
                return holder;
            }
            case MENU_VIEW: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ck_detail_menu, parent, false);
                return new CkDetailItemViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position ==0){
            headerholder = (CkDetailHeaderViewHolder)holder;
            headerholder.setThumbnails(thumbnailsDatas);
            headerholder.setData(datas);
            headerholder.setSchedule(scheduleList);
            headerholder.setOnCalendarHeaderViewClickListener(this);
            headerholder.setOnMapClickListener(this);
        }else{
            menuholder = (CkDetailItemViewHolder)holder;
            menuholder.setData(menuList.get(position-1));
            menuholder.setOnMEnuSwipeClickListener(this);
            mItemManger.setMode(Attributes.Mode.Multiple);
            mItemManger.bindView(menuholder.itemView, position-1);
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
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_ck_detail;
    }

    @Override
    public void onMapClick(View view, CkDetailData data) {
        if(mapListener!=null){
            mapListener.onMapClick(view, data);
        }
    }

    @Override
    public void onCalendarHeaderViewClickListener(View view, CalendarItem data, int position) {
        if(listener!=null){
            listener.onDetailAdapterClick(view, data, position);
        }
        if(data.isSelect){
            headerholder.showSchedule(data);
            calendarItem = data;
        }
    }

    public CalendarItem getSelectCalendarItem(){
        if(calendarItem!=null){
            return calendarItem;
        }
        return null;
    }

    @Override
    public void onMenuSwipeClick(View view, CkDetailMenuData data, int position, boolean select) {
        if(menuListener!=null){
            menuListener.onMenuSwipeClick(view, data, position, select);
        }
    }

    public interface OnMapClickListener{
        public void onMapClick(View view, CkDetailData data);
    }
    OnMapClickListener mapListener;
    public void setOnMapClickListener(OnMapClickListener listener){
        this.mapListener = listener;
    }

    public interface OnDetailAdapterClickListener{
        public void onDetailAdapterClick(View view, CalendarItem data, int position);
    }
    OnDetailAdapterClickListener listener;
    public void setOnDetailAdapterClickListener(OnDetailAdapterClickListener listener){
        this.listener = listener;
    }

    public interface OnMenuSwipeClickListener{
        public void onMenuSwipeClick(View view, CkDetailMenuData data, int position, boolean select);
    }

    OnMenuSwipeClickListener menuListener;
    public void setOnMEnuSwipeClickListener(OnMenuSwipeClickListener listener){
        menuListener = listener;
    }
}
