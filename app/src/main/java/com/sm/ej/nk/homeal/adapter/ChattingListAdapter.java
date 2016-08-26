package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.ChattingActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.viewholder.ChattingListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListViewHolder>{

    Context context;
    Intent intent;
    List<Drawable> items = new ArrayList<>();

    public void add(Drawable foodview){
        items.add(foodview);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(ChattingListViewHolder holder, int position) {
        holder.setImage(items.get(position));

        holder.getChattingListView().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.getLastConversation().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public ChattingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chattinglist, parent, false);
        context = parent.getContext();
        intent= new Intent(parent.getContext(), ChattingActivity.class);
        return new ChattingListViewHolder(view);
    }
}
