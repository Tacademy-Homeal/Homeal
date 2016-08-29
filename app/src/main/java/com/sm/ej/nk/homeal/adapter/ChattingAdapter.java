package com.sm.ej.nk.homeal.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.viewholder.ChattingReceiveViewHolder;
import com.sm.ej.nk.homeal.viewholder.ChattingSendViewHolder;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ChattingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Cursor cursor;

    private static final int VIEW_TYPE_SEND = 0;
    private static final int VIEW_TYPE_RECEIVE = 1;

    public void changeCursor(Cursor c){
        if(cursor != null){
            cursor.close();
        }
        cursor = c;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        cursor.moveToPosition(position);

        int type = cursor.getInt(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_TYPE));

        switch (type) {
            case ChatContract.ChatMessage.TYPE_SEND:
                return VIEW_TYPE_SEND;
            case ChatContract.ChatMessage.TYPE_RECEIVE:
                return VIEW_TYPE_RECEIVE;
        }

        throw new IllegalArgumentException("invalid type");
    }


    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_SEND : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chatting_send, parent, false);
                return new ChattingSendViewHolder(view);
            }
            case VIEW_TYPE_RECEIVE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chatting_receive, parent, false);
                return new ChattingReceiveViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SEND : {
                ChattingSendViewHolder svh = (ChattingSendViewHolder)holder;
                String message = cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_MESSAGE));
                svh.setMessage(message);
                break;
            }
            case VIEW_TYPE_RECEIVE : {
                ChattingReceiveViewHolder rvh = (ChattingReceiveViewHolder)holder;
                String message = cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_MESSAGE));
                rvh.setMessage(message);
                break;
            }
        }
    }


}
