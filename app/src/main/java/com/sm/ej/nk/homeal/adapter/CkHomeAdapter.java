package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.MapActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkInfoResult;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.viewholder.CkHomeHeaderViewHolder;
import com.sm.ej.nk.homeal.viewholder.CkHomeItemViewHolder;

import java.util.List;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CkHomeHeaderViewHolder.OnCalendarClickLIsener, CkHomeHeaderViewHolder.OnReviewClickListener{

    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    private CkDetailData headerData;
    private Context context;
    private List<CkDetailMenuData> menuList;
    private List<CkScheduleData> scheduleList;
    private CkHomeHeaderViewHolder headerholder;

    public void setResult(CkInfoResult result){
        this.headerData = result.getCooker_info();
        this.menuList = result.getCooker_menu();
        this.scheduleList = result.getCooker_schedule();
    }

    public void changeMenu(List<CkDetailMenuData> datas){
        menuList.clear();
        this.menuList = datas;
        notifyDataSetChanged();
    }

    public void changeSchedule(List<CkScheduleData> datas){
        scheduleList.clear();
        this.scheduleList = datas;
        notifyDataSetChanged();
    }

    public void deleteMenu(CkDetailMenuData data){
        menuList.remove(data);
        notifyDataSetChanged();
    }

    public void setHeader(CkDetailData data){
        headerData = data;
        notifyDataSetChanged();
    }

    public void additem(List<CkDetailMenuData> itemData){
        if(this.menuList==null){
            this.menuList = itemData;
        }else{
            this.menuList.addAll(itemData);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0){
            return TYPE_HEADER;
        }else{
            return TYPE_ITEM;
        }
    }

    private boolean isVisible=false;
    public void setvisivle(){
        isVisible=true;
        notifyDataSetChanged();
    }

    public void setinvisible(){
        isVisible = false;
        notifyDataSetChanged();
    }

    public CkHomeAdapter(Context context){
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case TYPE_HEADER:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_home_header, parent, false);
                return new CkHomeHeaderViewHolder(context,view);
            }
            case TYPE_ITEM:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_home_item, parent, false);
                return new CkHomeItemViewHolder(view, context);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if(position ==0){
            headerholder = (CkHomeHeaderViewHolder)holder;
            headerholder.setData(headerData);
            headerholder.setCalendar(scheduleList);
            headerholder.setOnCalendarCickListener(this);
            headerholder.setOnReviewClickListener(this);
            headerholder.userMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MapActivity.class);
                    intent.putExtra(MapActivity.INTENT_MAP, headerData);
                    context.startActivity(intent);
                }
            });
        }else{
            CkHomeItemViewHolder itemholder = (CkHomeItemViewHolder)holder;
            itemholder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(viewListener!=null){
                        viewListener.onHomeViewClick(view, position, menuList.get(position-1));
                    }
                }
            });
            itemholder.editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onHomeAdapterClick(view, position, menuList.get(position-1));
                    }
                }
            });
            itemholder.setOnMenuDeleteClickLIstener(new CkHomeItemViewHolder.OnMenuDeleteClickListener() {
                @Override
                public void onMenuDeleteClick(View view, CkDetailMenuData menuData) {
                    if(deleteListener!=null){
                        deleteListener.onMenuDeleteClick(view, menuData);
                    }
                }
            });
            if(isVisible){
                itemholder.visibleImage();
            }else{
                itemholder.invisibleImage();
            }
            itemholder.setData(menuList.get(position-1));
        }
    }

    @Override
    public int getItemCount() {
        int size=0;
        size=menuList.size()+1;
        return size;
    }

    public interface OnHomeAdapterClickListener{
        public void onHomeAdapterClick(View view, int position, CkDetailMenuData data);
    }
    OnHomeAdapterClickListener listener;
    public void setOnHomeAdapterClickListner(OnHomeAdapterClickListener listener){
        this.listener = listener;
    }

    public interface  OnHomeViewClickListener{
        public void onHomeViewClick(View view, int position, CkDetailMenuData data);
    }
    OnHomeViewClickListener viewListener;
    public void setOnHomeViewClick(OnHomeViewClickListener listener){
        viewListener = listener;
    }

    @Override
    public void onCalendarClick(View view, CalendarItem data, int position) {
        if(data.isSelect){
            headerholder.showSchedule(data);
        }
    }

    public interface OnMenuDeleteClickLIstener{
        public void onMenuDeleteClick(View view, CkDetailMenuData data);
    }

    OnMenuDeleteClickLIstener deleteListener;
    public void setOnMenuDeleteClickListener(OnMenuDeleteClickLIstener listener){
        deleteListener = listener;
    }

    @Override
    public void onReviewClick(View view) {
        if(reviewListener!=null){
            reviewListener.onReviewCLick(view, headerData);
        }
    }

    public interface OnReviewCLickLIstener{
        public void onReviewCLick(View view, CkDetailData data);
    }
    OnReviewCLickLIstener reviewListener;
    public void setOnReviewClickListener(OnReviewCLickLIstener listener){
        this.reviewListener = listener;
    }
}
