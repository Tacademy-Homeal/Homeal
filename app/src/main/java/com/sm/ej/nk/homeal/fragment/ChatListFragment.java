package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sm.ej.nk.homeal.ChattingActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.data.User;
import com.sm.ej.nk.homeal.manager.ChattingDBManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {

    @BindView(R.id.chatting_listview)
    ListView listView;

    SimpleCursorAdapter mAdapter;


    public static ChatListFragment createInstance(){
        final ChatListFragment pageFragment = new ChatListFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;

    }

    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat_list, container, false);
        ButterKnife.bind(this, view);
        listView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] from = {ChatContract.ChatUser.COLUMN_NAME, ChatContract.ChatUser.COLUMN_EMAIL, ChatContract.ChatMessage.COLUMN_MESSAGE};
        int[] to = {R.id.text_chattlist_name, R.id.text_chattlist_email,R.id.text_chattlist_last_message};
        mAdapter = new SimpleCursorAdapter(getContext(),R.layout.view_chat_user,null,from,to,0);
    }

    @OnItemClick(R.id.chatting_listview)
    public void onItemClick(int position, long id){
        Cursor cursor = (Cursor)listView.getItemAtPosition(position);
        User user = new User();
        user.setId((cursor.getLong(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID))));
        user.setEmail(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_EMAIL)));
        user.setUserName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
        Intent intent = new Intent(getContext(),ChattingActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER, user);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        Cursor c = ChattingDBManager.getInstance().getChatUser();
        mAdapter.changeCursor(c);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
    }



}
