package com.sm.ej.nk.homeal.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.User;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ChattingListViewHolder  extends RecyclerView.ViewHolder{

    private ImageView chattingListView;
    private TextView lastConversation;

    public ChattingListViewHolder(View itemView) {
        super(itemView);
        chattingListView = (ImageView)itemView.findViewById(R.id.image_chatting);
        lastConversation = (TextView)itemView.findViewById(R.id.text_chatting_list);
    }

    public void setImage(Drawable testView){
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

    public void setData(User user){
        Glide.with(chattingListView.getContext()).load(user.getUserImageUrl()).into(chattingListView);

        //change lastConversation
        lastConversation.setText(user.getUserName());
    }
}
