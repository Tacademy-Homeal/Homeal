package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mukesh.countrypicker.fragments.CountryPicker;
import com.mukesh.countrypicker.interfaces.CountryPickerListener;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.PersonalData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.manager.TranslateManager;
import com.sm.ej.nk.homeal.request.CkInfoRequest;
import com.sm.ej.nk.homeal.request.CkInfoupdateRequest;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CkPersonalDataActivity extends AppCompatActivity {

    private int iYear, iMonth, iDay;
    static final int DATE_DIALOG_ID = 0;
    private File imageFile = null;
    private File file;
    double latitude, longitude;
    private CountryPicker countryPicker;
    public static Context mContext;
    PersonalData ckdata;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_ck_name_last)
    EditText nameEdit;

    @BindView(R.id.text_ck_address)
    TextView addressText;

    @BindView(R.id.edit_ck_introduce)
    EditText introduceEdit;

    @BindView(R.id.edit_ck_phone)
    EditText phoneEdit;

    @BindView(R.id.text_ck_birth)
    TextView birthText;

    @BindView(R.id.text_ck_country)
    TextView countryText;

    @BindView(R.id.radio_ck_male)
    RadioButton maleRadio;

    @BindView(R.id.radio_ck_female)
    RadioButton femaleRadio;

    @BindView(R.id.btn_ck_changefinish)
    Button btnChangeFinish;

    @BindView(R.id.btn_personal_change)
    Button btnChange;

    @BindView(R.id.image_ck_picture)
    ImageView ckpictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_personal_data);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        PersonalData ckdata = (PersonalData) intent.getSerializableExtra(CkMyPageFragment.CK_DATA);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.PersonaldataActivity_appbar));

        final Calendar objTime = Calendar.getInstance();
        iYear = objTime.get(Calendar.YEAR);
        iMonth = objTime.get(Calendar.MONTH);
        iDay = objTime.get(Calendar.DAY_OF_MONTH);
        countryPicker = CountryPicker.newInstance(getString(R.string.select_country));
        setListner();
        isPersonalData(false);
        btnChangeFinish.setVisibility(View.GONE);
        mContext = this;

        CkInfoRequest request = new CkInfoRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PersonalData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PersonalData>> request, NetworkResult<PersonalData> result) {
                ckdata = result.getResult();
                nameEdit.setText(ckdata.getName());
                addressText.setText(ckdata.getAddress());
                introduceEdit.setText(ckdata.getIntroduce());
                phoneEdit.setText(ckdata.getPhone());
                countryText.setText(ckdata.getCountry());
                birthText.setText(ckdata.getBirth());
                if (ckdata.getGender().equals("Male")) {
                    radioGroup.check(R.id.radio_ck_male);
                } else {
                    radioGroup.check(R.id.radio_ck_female);
                }
                Glide.with(ckpictureView.getContext()).load(ckdata.getImage()).into(ckpictureView);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PersonalData>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
//        setUserImage(ckdata);
    }

    private void setListner() {
        countryPicker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                countryText.setText(name);
                countryPicker.dismiss();
            }
        });
    }
    @OnClick(R.id.text_ck_country)
    public void onCountry(){
        countryPicker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    public static final int MODE_GET_IMAGE = 3;
    private static final int GET_IMAGE = 35;

    @OnClick(R.id.image_ck_picture)
    public void onckGallery() {
        Intent intent = new Intent(CkPersonalDataActivity.this, GalleryActivity.class);
        intent.putExtra(GalleryActivity.INTENT_MODE, MODE_GET_IMAGE);
        startActivityForResult(intent, GET_IMAGE);
    }

    static final int ADDRESS_SEARCH = 1;

    @OnClick(R.id.text_ck_address)
    public void onAddressEdit() {
        Intent intent = new Intent(CkPersonalDataActivity.this, AddressEditActivity.class);
        startActivityForResult(intent, ADDRESS_SEARCH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == ADDRESS_SEARCH) {
            if (resultCode == RESULT_OK) {
                String address = intent.getStringExtra("address");
                TranslateManager.getInstance().translateKoreantoEng(address);
                latitude = intent.getExtras().getDouble("latitude");
                longitude = intent.getExtras().getDouble("longitude");
                file = new File(intent.getExtras().getString("imageFile"));
            }
        }
        if (requestCode == GET_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                String imagePath = intent.getStringExtra(GalleryActivity.IMAGE_PATH);
                imageFile = new File(imagePath);
                Glide.with(this).load(imageFile).into(ckpictureView);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    public void setTranslate(String translate) {
        addressText.setText(translate);
    }

//
//    public void setUserImage(PersonalData ckdata) {
//        nameEdit.setText(ckdata.getName());
//        addressText.setText(ckdata.getAddress());
//        introduceEdit.setText(ckdata.getIntroduce());
//        phoneEdit.setText(ckdata.getPhone());
//        countryText.setText(ckdata.getCountry());
//        birthText.setText(ckdata.getBirth());
//        if (ckdata.getGender().equals("Male")) {
//            radioGroup.check(R.id.radio_ck_male);
//        } else {
//            radioGroup.check(R.id.radio_ck_female);
//        }
//
//        Glide.with(ckpictureView.getContext()).load(ckdata.getImage()).into(ckpictureView);
//    }

    @OnClick(R.id.text_ck_birth)
    public void onBirth() {
        showDialog(DATE_DIALOG_ID);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener,
                        iYear, iMonth, iDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    iYear = year;
                    iMonth = monthOfYear;
                    iDay = dayOfMonth;
                    birthText.setText("" + iYear + "-" + (iMonth + 1) + "-" + iDay);
                }
            };

    @OnClick(R.id.btn_personal_change)
    public void onPersonalChanged() {
        isPersonalData(true);
        btnChangeFinish.setVisibility(View.VISIBLE);
        btnChange.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btn_ck_changefinish)
    public void onChangefinish() {
        String gender;
        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_ck_male) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        CkInfoupdateRequest request = new CkInfoupdateRequest(CkPersonalDataActivity.this, nameEdit.getText().toString(), birthText.getText().toString(), phoneEdit.getText().toString(), introduceEdit.getText().toString(), addressText.getText().toString(), gender, latitude, longitude, imageFile, file, countryText.getText().toString());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
//                Toast.makeText(CkPersonalDataActivity.this, ""+longitude, Toast.LENGTH_SHORT).show();
                Toast.makeText(CkPersonalDataActivity.this, "수정완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(CkPersonalDataActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        isPersonalData(false);
        btnChangeFinish.setVisibility(View.GONE);
        btnChange.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void isPersonalData(boolean s) {
        nameEdit.setEnabled(s);
        addressText.setEnabled(s);
        introduceEdit.setEnabled(s);
        phoneEdit.setEnabled(s);
        birthText.setEnabled(s);
        countryText.setEnabled(s);
        maleRadio.setEnabled(s);
        femaleRadio.setEnabled(s);
        ckpictureView.setEnabled(s);
    }
}
