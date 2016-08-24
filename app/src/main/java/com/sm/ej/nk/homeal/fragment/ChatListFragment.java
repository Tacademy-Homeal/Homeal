package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.CkMainActivity;
import com.sm.ej.nk.homeal.EtMainActivity;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {

    @BindView(R.id.btn_chatlist)
    Button btn_chatlist;

    private int num;

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
        return view;
    }

    @OnClick(R.id.btn_chatlist)
    public void moveChattingActivity() {
        if(HomealApplication.isCooker()){

            ((CkMainActivity)getActivity()).moveChattigActivity();

        }else{
            ((EtMainActivity)getActivity()).moveChattigActivity();
        }

    }
}
