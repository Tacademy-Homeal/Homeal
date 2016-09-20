package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.EtPersonalDataActivity;
import com.sm.ej.nk.homeal.EtZzimActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SettingActivity;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.PersonalData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.EaterInfoRequest;

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

    PersonalData data;
    public static final String ET_DATA = "eater_data";

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

        EaterInfoRequest request = new EaterInfoRequest(getContext());

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PersonalData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PersonalData>> request, NetworkResult<PersonalData> result) {
                data = result.getResult();
                etnameView.setText(data.getName());
                ettypeView.setText(data.getType());
                Glide.with(etpictureView.getContext()).load(data.getImage()).into(etpictureView);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PersonalData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), ""+errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    @OnClick(R.id.text_et_mypage_personal)
    public void changeEtPersonalDataActivity() {
        Intent intent = new Intent(getActivity(), EtPersonalDataActivity.class);
        intent.putExtra(ET_DATA,data);
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
