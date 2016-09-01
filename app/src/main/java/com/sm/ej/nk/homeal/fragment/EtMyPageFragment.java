package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.EtPersonalDataActivity;
import com.sm.ej.nk.homeal.EtZzimActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SettingActivity;
import com.sm.ej.nk.homeal.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtMyPageFragment extends Fragment {

    @BindView(R.id.image_et_picture)
    ImageView etpictureView;

    @BindView(R.id.text_et_name)
    TextView etnameView;

    @BindView(R.id.text_et_type)
    TextView ettypeView;

    @BindView(R.id.text_et_point)
    TextView etpointView;
    //


    public static EtMyPageFragment createInstance() {
        final EtMyPageFragment pageFragment = new EtMyPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtMyPageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_et_my_page, container, false);
        ButterKnife.bind(this, view);

        initData();
        setUser();
        return view;

    }

    User user;

    public void setUser() {

        Glide.with(etpictureView.getContext()).load(user.getPictureUrl()).into(etpictureView);
        etnameView.setText(user.getUserName());
        ettypeView.setText(user.getType());
        etpointView.setText(""+user.getEtPoint()+"P");
    }

    private void initData() {
        user = new User();
        user.setPictureUrl("http://cfile22.uf.tistory.com/image/264785445579217E20ADEE");
        user.setUserName("Eunji");
        user.setType("Eater");
        user.setEtPoint(1300);
    }


    @OnClick(R.id.text_et_mypage_personal)
    public void changeEtPersonalDataActivity() {
        Intent intent = new Intent(getActivity(), EtPersonalDataActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.text_et_mypage_setting)
    public void changeSettingActivity() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.text_et_mypage_zzim)
    public void changeZzimActivity() {
        Intent intent = new Intent(getActivity(), EtZzimActivity.class);
        startActivity(intent);
    }
}
