package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkHomeData;
import com.sm.ej.nk.homeal.data.CkHomeItemData;
import com.sm.ej.nk.homeal.viewholder.CkHomeHeaderViewHolder;
import com.sm.ej.nk.homeal.viewholder.CkHomeItemViewHolder;

import java.util.List;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    private CkHomeData headerData;
    private List<CkHomeItemData> itemData;

    public void setHeader(CkHomeData data){
        headerData = data;
        notifyDataSetChanged();
    }

    public void additem(List<CkHomeItemData> itemData){
        if(this.itemData==null){
            this.itemData = itemData;
        }else{
            this.itemData.addAll(itemData);
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case TYPE_HEADER:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_home_header, parent, false);
                return new CkHomeHeaderViewHolder(view);
            }
            case TYPE_ITEM:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_home_item, parent, false);
                return new CkHomeItemViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position ==0){
            CkHomeHeaderViewHolder headerholder = (CkHomeHeaderViewHolder)holder;

        }else{

        }
    }

    @Override
    public int getItemCount() {
        int size=0;
        size=itemData.size()+1;
        return size;
    }
}
