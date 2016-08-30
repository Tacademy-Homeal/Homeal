package com.sm.ej.nk.homeal.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.ChattingListAdapter;
import com.sm.ej.nk.homeal.manager.ChattingDBManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {

    @BindView(R.id.rv_chattinglist)
    RecyclerView rv_chattinglist;

    RecyclerView.LayoutManager layoutManager;

    ChattingListAdapter mAdapter;

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

        mAdapter = new ChattingListAdapter();

        rv_chattinglist.setLayoutManager(layoutManager);
        rv_chattinglist.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        /*
        //Chatting list setting
        String[] from = {ChatContract.ChatUser.COLUMN_NAME, ChatContract.ChatUser.COLUMN_EMAIL, ChatContract.ChatMessage.COLUMN_MESSAGE};
        int[] to = {R.id.text_chattlist_name, R.id.text_chattlist_email,R.id.text_chattlist_last_message};
*/
    }
/*

    @OnItemClick(R.id.rv_chattinglist)
    public void onItemClick(int position, long id){

       Cursor cursor = (Cursor)listView.getItemAtPosition(position);
        User user = new User();
        user.setId((cursor.getLong(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID))));
        user.setEmail(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_EMAIL)));
        user.setUserName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
        Intent intent = new Intent(getContext(),ChattingActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER, user);
        startActivity(intent);*//*

    }
*/
    private void updateMessage() {
        Cursor c = ChattingDBManager.getInstance().getChatUser();
        mAdapter.changeCursor(c);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMessage();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
    }
}
