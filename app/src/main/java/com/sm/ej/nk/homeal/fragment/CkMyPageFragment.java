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
import com.sm.ej.nk.homeal.CkPersonalDataActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.PersonalData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkInfoRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CkMyPageFragment extends Fragment {
    @BindView(R.id.image_ck_picture)
    ImageView ckpictureView;

    @BindView(R.id.text_ck_name)
    TextView cknameView;


//    @BindView(R.id.progress_ck_mypage_total)
//    ProgressBar cktotalView;

    PersonalData data;
    public static final String CK_DATA = "Ck_data";
    public static CkMyPageFragment createInstance() {
        final CkMyPageFragment pageFragment = new CkMyPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public CkMyPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ck_my_page, container, false);
        ButterKnife.bind(this, view);

        CkInfoRequest request = new CkInfoRequest(getContext());

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PersonalData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PersonalData>> request, NetworkResult<PersonalData> result) {
                    data = result.getResult();
                    cknameView.setText(data.getName());
//                    cktypeView.setText(data.getType());
//                    cktotalView.setProgress((int)data.getGrade());
                    Glide.with(ckpictureView.getContext()).load(data.getImage()).into(ckpictureView);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PersonalData>> request, int errorCode, String errorMessage, Throwable e) {
            }
        });


        return view;
    }

    @OnClick(R.id.text_ck_mypage_personal)
    public void onCkMypagePersonal() {
        Intent intent = new Intent(getActivity(), CkPersonalDataActivity.class);
        intent.putExtra(CK_DATA,data);
        startActivity(intent);
    }

//    @OnClick(R.id.text_ck_mypage_setting)
//    public void onCkMypageSetting() {
//        Intent intent = new Intent(getActivity(), SettingActivity.class);
//        startActivity(intent);
//    }

}
