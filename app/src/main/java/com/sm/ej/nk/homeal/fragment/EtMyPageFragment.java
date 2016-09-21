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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.AdviceActivity;
import com.sm.ej.nk.homeal.EtPersonalDataActivity;
import com.sm.ej.nk.homeal.EtZzimActivity;
import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.PersonalData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.EaterInfoRequest;
import com.sm.ej.nk.homeal.request.LogOutRequest;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtMyPageFragment extends Fragment {

    public static int str = 0;

    @BindView(R.id.spinner_language_setting)
    Spinner languageSpinner;

    ArrayAdapter<String> languageAdapter;

    @BindView(R.id.image_et_picture)
    ImageView etpictureView;

    @BindView(R.id.text_et_name)
    TextView etnameView;

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


        languageAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.language));
        languageAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        languageSpinner.setAdapter(languageAdapter);
        languageSpinner.setSelection(str);

        EaterInfoRequest request = new EaterInfoRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PersonalData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PersonalData>> request, NetworkResult<PersonalData> result) {
                data = result.getResult();
                etnameView.setText(data.getName());
//                ettypeView.setText(data.getType());
                Glide.with(etpictureView.getContext()).load(data.getImage()).into(etpictureView);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PersonalData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "" + errorMessage, Toast.LENGTH_SHORT).show();
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
                        Spinner languageSpinner = (Spinner)adapterView.findViewById(R.id.spinner_language_setting);
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
                        languageSpinner = (Spinner)adapterView.findViewById(R.id.spinner_language_setting);
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

    @OnClick(R.id.linear_et_mapage_top)
    public void changeEtPersonalDataActivity() {
        Intent intent = new Intent(getActivity(), EtPersonalDataActivity.class);
        intent.putExtra(ET_DATA, data);
        startActivity(intent);

    }

    @OnClick(R.id.linear_et_mapage_middle)
    public void changeZzimActivity() {
        Intent intent = new Intent(getActivity(), EtZzimActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.linear_et_faq)
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
