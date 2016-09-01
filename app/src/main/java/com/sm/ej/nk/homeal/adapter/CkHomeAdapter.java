package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
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
    private Context context;



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
                return new CkHomeHeaderViewHolder(view);
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
            CkHomeHeaderViewHolder headerholder = (CkHomeHeaderViewHolder)holder;
            headerholder.setData(headerData);
        }else{
            CkHomeItemViewHolder itemholder = (CkHomeItemViewHolder)holder;
            itemholder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(viewListener!=null){
                        viewListener.onHomeViewClick(view, position, itemData.get(position-1));
                    }
                }
            });
            itemholder.editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onHomeAdapterClick(view, position, itemData.get(position-1));
                    }
                }
            });
            if(isVisible){
                itemholder.visibleImage();
            }else{
                itemholder.invisibleImage();
            }
            itemholder.setData(itemData.get(position-1));
        }
    }

    @Override
    public int getItemCount() {
        int size=0;
        size=itemData.size()+1;
        return size;
    }

    public interface OnHomeAdapterClickListener{
        public void onHomeAdapterClick(View view, int position, CkHomeItemData data);
    }
    OnHomeAdapterClickListener listener;
    public void setOnHomeAdapterClickListner(OnHomeAdapterClickListener listener){
        this.listener = listener;
    }

    public interface  OnHomeViewClickListener{
        public void onHomeViewClick(View view, int position, CkHomeItemData data);
    }
    OnHomeViewClickListener viewListener;
    public void setOnHomeViewClick(OnHomeViewClickListener listener){
        viewListener = listener;
    }
}
