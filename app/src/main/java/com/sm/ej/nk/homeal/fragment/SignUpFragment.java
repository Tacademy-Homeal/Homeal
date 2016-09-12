package com.sm.ej.nk.homeal.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.FacebookUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @BindView(R.id.sf_ok)
    Button sf_ok;

    @BindView(R.id.edit_frist_name)
    EditText fristnameEdit;

    @BindView(R.id.edit_second_name)
    EditText secondnameEdit;

    @BindView(R.id.text_signup_birth)
    TextView birthText;

    @BindView(R.id.image_sign_up_person)
    ImageView personView;

    @BindView(R.id.edit_signup_intro)
    EditText introText;

    @BindView(R.id.edit_signup_phone)
    EditText phoneEdit;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    public static final String ARG_FACEBOOK_USER = "facebookUser";

    public static SignUpFragment newInstance(FacebookUser user) {
        SignUpFragment f = new SignUpFragment();
        Bundle b = new Bundle();
        b.putSerializable(ARG_FACEBOOK_USER, user);
        f.setArguments(b);
        return f;
    }


    public SignUpFragment() {


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.sf_ok)
    public void onSingUpOk() {
//        if(!TextUtils.isEmpty(editText.getText().toString())){
//        }else{
//            showDialog();
//        }
//        final String gender;
//        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_ck_male) {
//            gender = "Male";
//        } else {
//            gender = "Female";
//        }
//        SignupRequest request = new SignupRequest(getContext(), fristnameEdit.getText().toString(), birthText.getText().toString(), phoneEdit.getText().toString(), introText.getText().toString(), gender);
//
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
//            @Override
//            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
//                Toast.makeText(getContext(), "회원정보 성공", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
//                Toast.makeText(getContext(), "" + errorMessage, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), ""+fristnameEdit.getText().toString()+""+ birthText.getText().toString()+""+ phoneEdit.getText().toString()+""+introText.getText().toString()+""+gender,Toast.LENGTH_SHORT).show();
//            }
//        });

        ((LoginActivity) getActivity()).moveMainActivity();
    }

    @OnClick(R.id.text_signup_birth)
    public void onBirth() {
//        Toast.makeText(getContext(), "눌림", Toast.LENGTH_SHORT).show();
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "datePicker");
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.sign_up));
        builder.show();
    }

    private static final int GET_IMAGE = 1;


}
