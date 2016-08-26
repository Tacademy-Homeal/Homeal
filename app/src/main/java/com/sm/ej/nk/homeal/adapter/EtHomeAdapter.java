package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.viewholder.EtHomeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtHomeAdapter extends RecyclerView.Adapter<EtHomeViewHolder> {
    List<EtHomeData> list = new ArrayList<>();

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addList(List<EtHomeData> list){
      this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public EtHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_et_home, parent, false);

        return new EtHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EtHomeViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
