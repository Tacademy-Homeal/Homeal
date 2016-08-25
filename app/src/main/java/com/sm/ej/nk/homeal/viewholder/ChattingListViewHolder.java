package com.sm.ej.nk.homeal.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ChattingListViewHolder  extends RecyclerView.ViewHolder{

    ImageView chattingListView;
    TextView lastConversation;

    public ChattingListViewHolder(View itemView) {
        super(itemView);
        chattingListView = (ImageView)itemView.findViewById(R.id.image_chatting_list);
        lastConversation = (TextView)itemView.findViewById(R.id.text_chatting_list);
    }

    public void setChattingView(Drawable testView){
        chattingListView.setImageDrawable(testView);

    }

    public ImageView getChattingListView() {
        return chattingListView;
    }

    public void setChattingListView(ImageView chattingListView) {
        this.chattingListView = chattingListView;
    }

    public TextView getLastConversation() {
        return lastConversation;
    }

    public void setLastConversation(TextView lastConversation) {
        this.lastConversation = lastConversation;
    }
}
