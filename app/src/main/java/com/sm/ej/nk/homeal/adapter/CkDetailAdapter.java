package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.viewholder.CkDetailHeaderViewHolder;
import com.sm.ej.nk.homeal.viewholder.CkDetailItemViewHolder;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    CkDetailData datas;

    public CkDetailAdapter(CkDetailData datas){
        this.datas = datas;
    }

    private static final int HEADER_VIEW = 1;
    private static final int MENU_VIEW = 2;

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
                return new CkDetailHeaderViewHolder(parent.getContext(), view);
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
            CkDetailItemViewHolder menuholder = (CkDetailItemViewHolder)holder;
            menuholder.setData(datas.menuList.get(position-1));
        }
    }

    @Override
    public int getItemCount() {
        int size=0;
        if(!datas.menuList.isEmpty()){
            size=datas.menuList.size()+1;
        }
        return size;
    }
}
