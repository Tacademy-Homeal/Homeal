package com.sm.ej.nk.homeal.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.viewholder.ChattingListViewHolder;

/**
 * Created by Tacademy on 2016-08-25.
 */
public  class ChattingListAdapter extends RecyclerView.Adapter<ChattingListViewHolder>{

    private Cursor cursor;


    public void changeCursor(Cursor cursor){
        if(cursor != null){
            cursor.close();
        }
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public Cursor getCursor(){
        return cursor;
    }

    @Override
    public ChattingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chattinglist, parent, false);
        return new ChattingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChattingListViewHolder holder, int position) {

        cursor.moveToPosition(position);

        String chatting = cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID));
        String serverId = cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_IMAGE));

        holder.setChattingListView(chatting);
        holder.setServerId(serverId);

    }

}
