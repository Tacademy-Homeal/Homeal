package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.viewholder.CkDetailItemViewHolder;
import com.sm.ej.nk.homeal.viewholder.ReserveRequestBodyViewHolder;

import java.util.List;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ReserveRequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CkDetailMenuData> menuDatas;

    private static final int BODY_VIEW=0;
    private static final int MENU_VIEW = 1;

    public void addMenu(List<CkDetailMenuData> datas){
        this.menuDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return MENU_VIEW;
        }else{
            return BODY_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case MENU_VIEW:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ck_detail_menu, parent, false);
                return new CkDetailItemViewHolder(view);
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
            ReserveRequestBodyViewHolder itemViewHolder = (ReserveRequestBodyViewHolder)holder;
//            itemViewHolder.setCalendarData();
        }else{
            CkDetailItemViewHolder itemViewHolder = (CkDetailItemViewHolder)holder;
//            itemViewHolder.setData();
        }
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
