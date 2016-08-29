package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.viewholder.ChattingListViewHolder;
import com.sm.ej.nk.homeal.viewholder.ChattingSendViewHolder;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListViewHolder>{

    Cursor cursor;

    CursorAdapter mCursorAdapter;
    Context mContext;
    private String[] from;
    private int[] to;

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    @Override
    public ChattingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chattinglist, parent, false);
        return new ChattingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChattingListViewHolder holder, int position) {


        cursor.moveToPosition(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SEND : {
                ChattingSendViewHolder svh = (ChattingSendViewHolder)holder;
                String message = cursor.getString(cursor.getColumnIndex(ChatContract.ChatMessage.COLUMN_MESSAGE));
                svh.setMessage(message);
                break;
            }
    }
}
