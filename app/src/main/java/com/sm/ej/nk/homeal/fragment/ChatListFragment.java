package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.ChattingActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.ChattingListAdapter;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.data.User;
import com.sm.ej.nk.homeal.manager.ChattingDBManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment  implements ChattingListAdapter.OnViewClickListener{
    public static final int TYPE_SEND = 0;
    public static final int TYPE_RECEIVE = 1;
    @BindView(R.id.rv_chattinglist)
    RecyclerView rv_chattinglist;

    RecyclerView.LayoutManager layoutManager;
    ChattingListAdapter mAdapter;

    public static final String EXTRA_USER = "user";

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

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new ChattingListAdapter();
        rv_chattinglist.setAdapter(mAdapter);
        rv_chattinglist.setLayoutManager(layoutManager);
        mAdapter.setOnViewClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Cursor c = ChattingDBManager.getInstance().getChatUser();
        mAdapter.changeCursor(c);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
    }

    @Override
    public void onViewClick(View view,int position) {


        Cursor cursor = mAdapter.getCursor(position);
        User user = new User();
         user.setId(cursor.getLong(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
        long userid = user.getId();
        user.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
        Intent intent = new Intent(getContext(),ChattingActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER,userid);
        startActivity(intent);
    }

}
