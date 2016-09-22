package com.sm.ej.nk.homeal.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.AdviceActivity;
import com.sm.ej.nk.homeal.CkPersonalDataActivity;
import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.PersonalData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkInfoRequest;
import com.sm.ej.nk.homeal.request.LogOutRequest;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CkMyPageFragment extends Fragment {

    public static int str = 0;

    @BindView(R.id.spinner_language_setting)
    Spinner languageSpinner;

    ArrayAdapter<String> languageAdapter;

    @BindView(R.id.image_ck_picture)
    ImageView ckpictureView;

    @BindView(R.id.text_ck_name)
    TextView cknameView;


    @BindView(R.id.rating_ck_mypage_total)
    RatingBar cktotalView;

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

        languageAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.language));
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);
        languageSpinner.setSelection(str);

        CkInfoRequest request = new CkInfoRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PersonalData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PersonalData>> request, NetworkResult<PersonalData> result) {
                data = result.getResult();
                cknameView.setText(data.getName());
                cktotalView.setProgress((int) data.getGrade());
                Glide.with(ckpictureView.getContext()).load(data.getImage()).into(ckpictureView);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PersonalData>> request, int errorCode, String errorMessage, Throwable e) {
            }
        });

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                str = languageSpinner.getSelectedItemPosition();
                switch (position) {
                    case 0:
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Language", Context.MODE_PRIVATE);
//                        str =languageSpinner.getSelectedItemPosition();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Spinner languageSpinner = (Spinner) adapterView.findViewById(R.id.spinner_language_setting);
                        editor.putInt("ko", languageSpinner.getSelectedItemPosition());
                        editor.commit();
                        Locale locale = new Locale("ko");
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.locale = locale;
                        getContext().getResources().updateConfiguration(configuration, getContext().getResources().getDisplayMetrics());
                        break;

                    case 1:
                        sharedPreferences = getActivity().getSharedPreferences("Language", Context.MODE_PRIVATE);
//                        str =languageSpinner.getSelectedItemPosition();
                        editor = sharedPreferences.edit();
                        languageSpinner = (Spinner) adapterView.findViewById(R.id.spinner_language_setting);
                        editor.putInt("en", languageSpinner.getSelectedItemPosition());
                        editor.commit();
                        locale = new Locale("en");
                        Locale.setDefault(locale);
                        configuration = new Configuration();
                        configuration.locale = locale;
                        getContext().getResources().updateConfiguration(configuration, getContext().getResources().getDisplayMetrics());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "언어를 설정해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @OnClick(R.id.linear_ck_mypage_top)
    public void onCkMypagePersonal() {
        Intent intent = new Intent(getActivity(), CkPersonalDataActivity.class);
        intent.putExtra(CK_DATA, data);
        startActivity(intent);
    }

    @OnClick(R.id.linear_ck_faq)
    public void onSettingFaq() {
        Intent intent = new Intent(getActivity(), AdviceActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_ck_logout)
    public void onLogout() {
        LogOutRequest request = new LogOutRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "LogOut fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
