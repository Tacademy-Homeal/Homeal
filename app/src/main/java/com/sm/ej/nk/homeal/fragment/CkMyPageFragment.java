package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.CkPersonalDataActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SettingActivity;
import com.sm.ej.nk.homeal.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkMyPageFragment extends Fragment {
    @BindView(R.id.image_ck_picture)
    ImageView ckpictureView;

    @BindView(R.id.text_ck_name)
    TextView cknameView;

    @BindView(R.id.text_ck_type)
    TextView cktypeView;

    @BindView(R.id.progress_ck_mypage_total)
    ProgressBar cktotalView;

    public static CkMyPageFragment createInstance() {
        final CkMyPageFragment pageFragment = new CkMyPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public CkMyPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ck_my_page, container, false);
        ButterKnife.bind(this, view);

        initData();
        setUser();
        return view;
    }

    private void initData() {
        user = new User();
        user.setPictureUrl("http://img.etnews.com/news/article/2016/02/18/cms_temp_article_18165253593992.jpg");
        user.setUserName("Eunji");
        user.setType("Cooker");
        user.totalScore =3;
    }

    User user;

    public void setUser() {

        Glide.with(ckpictureView.getContext()).load(user.getPictureUrl()).into(ckpictureView);
        cknameView.setText(user.getUserName());
        cktypeView.setText(user.getType());
    }

    @OnClick(R.id.text_ck_mypage_personal)
    public void onCkMypagePersonal() {
        Intent intent = new Intent(getActivity(), CkPersonalDataActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.text_ck_mypage_setting)
    public void onCkMypageSetting() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

}
