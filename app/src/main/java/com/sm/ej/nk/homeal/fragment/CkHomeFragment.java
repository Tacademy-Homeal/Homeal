package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sm.ej.nk.homeal.MapActivity;
import com.sm.ej.nk.homeal.MenuDetailActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkHomeFragment extends Fragment {

    public CkHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ck_home, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    public static CkHomeFragment createInstance(){
        final CkHomeFragment pageFragment = new CkHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.image_info_ck_at_photo)
    public void onClickPhoto(){
        Toast.makeText(getContext(),"Photo Click",Toast.LENGTH_LONG).show();
    }

    //no chatting change
    @OnClick(R.id.btn_ck_at_confire)
    public void onClickChatting(){
        Toast.makeText(getContext(),"Complete",Toast.LENGTH_LONG).show();
    }

    //Deatil memu
    @OnClick(R.id.image_info_ck_at_menu)
    public void onClickMenu(){
        Intent intent = new Intent(getContext(), MenuDetailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.image_ck_at_map)
    public void onClickMap(){
        Intent intent = new Intent(getContext(), MapActivity.class);
        startActivity(intent);
    }

}
