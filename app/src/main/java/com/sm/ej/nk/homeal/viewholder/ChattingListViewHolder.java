package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ChattingListViewHolder  extends RecyclerView.ViewHolder {

    private ImageView chattingListView;
    private TextView serverIdView;

    public ChattingListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mClickListener != null){
                    mClickListener.onItemClick(v,getAdapterPosition());
                }
            }
        });
        chattingListView = (ImageView)itemView.findViewById(R.id.image_chatting_list);
        serverIdView = (TextView)itemView.findViewById(R.id.text_chatting_list);
    }

    public TextView getServerId() {
        return serverIdView;
    }

    public void setServerId(String serverId) {
        serverIdView.setText(serverId);
    }

    public ImageView getChattingListView() {return chattingListView;}

    public void setChattingListView(String chattingList) {Glide.with(chattingListView.getContext()).load(chattingList).into(chattingListView);}

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    OnItemClickListener mClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mClickListener = listener;
    }

}
