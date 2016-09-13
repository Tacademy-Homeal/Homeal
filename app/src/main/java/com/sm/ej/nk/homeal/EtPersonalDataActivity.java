package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import com.sm.ej.nk.homeal.fragment.EtMyPageFragment;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.EaterInfoRequest;
import com.sm.ej.nk.homeal.request.EtInfoupdateRequest;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EtPersonalDataActivity extends AppCompatActivity {
    private int iYear, iMonth, iDay;
    static final int DATE_DIALOG_ID = 0;
    PersonalData data;
    public static final int MODE_GET_IMAGE = 5;
    private static final int GET_IMAGE = 65;
    private File imageFile = null;
    private CountryPicker countryPicker;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_et_last_name)
    EditText nameEdit;

    @BindView(R.id.edit_et_introduce)
    EditText introduceEdit;

    @BindView(R.id.edit_et_phone)
    EditText phoneEdit;

    @BindView(R.id.text_et_birth)
    TextView birthText;

    @BindView(R.id.btn_personal_change)
    Button btnChange;

    @BindView(R.id.text_et_country)
    TextView countryText;

    @BindView(R.id.radio_et_male)
    RadioButton maleRadio;

    @BindView(R.id.radio_et_female)
    RadioButton femaleRadio;

    @BindView(R.id.btn_et_changefinish)
    Button btnChangeFinish;

    @BindView(R.id.image_et_picture)
    ImageView etpictureView;

    @OnClick(R.id.image_et_picture)
    public void onetGallery() {
        Intent intent = new Intent(EtPersonalDataActivity.this, GalleryActivity.class);
        intent.putExtra(GalleryActivity.INTENT_MODE, MODE_GET_IMAGE);
        startActivityForResult(intent, GET_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == GET_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                String imagePath = intent.getStringExtra(GalleryActivity.IMAGE_PATH);
                imageFile = new File(imagePath);
                Glide.with(this).load(imageFile).into(etpictureView);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_personal_data);

        Intent intent = getIntent();
        PersonalData etData = (PersonalData) intent.getSerializableExtra(EtMyPageFragment.ET_DATA);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.PersonaldataActivity_appbar));

        EaterInfoRequest request = new EaterInfoRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PersonalData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PersonalData>> request, NetworkResult<PersonalData> result) {
                data = result.getResult();
                nameEdit.setText(data.getName());
                introduceEdit.setText(data.getIntroduce());
                phoneEdit.setText(data.getPhone());
                birthText.setText(data.getBirth());
                countryText.setText(data.getCountry());

                if (data.getGender().equals("Male")) {
                    radioGroup.check(R.id.radio_et_male);
                } else {
                    radioGroup.check(R.id.radio_et_female);
                }
                Glide.with(etpictureView.getContext()).load(data.getImage()).into(etpictureView);
            }
            @Override
            public void onFail(NetworkRequest<NetworkResult<PersonalData>> request, int errorCode, String errorMessage, Throwable e) {
            }
        });
        countryPicker = CountryPicker.newInstance(getString(R.string.select_country));
        setListner();
        isPersonalData(false);
        btnChangeFinish.setVisibility(View.GONE);
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

    @OnClick(R.id.text_et_country)
    public void onCountry(){
        countryPicker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    @OnClick(R.id.text_et_birth)
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
        btnChange.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_et_changefinish)
    public void onChangefinish() {
        String gender;
        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_ck_male) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        EtInfoupdateRequest request = new EtInfoupdateRequest(EtPersonalDataActivity.this, nameEdit.getText().toString(), birthText.getText().toString(), phoneEdit.getText().toString(), introduceEdit.getText().toString(), gender, imageFile, countryText.getText().toString());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                Toast.makeText(EtPersonalDataActivity.this, "수정완료", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(EtPersonalDataActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        isPersonalData(false);
        btnChangeFinish.setVisibility(View.GONE);
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
        introduceEdit.setEnabled(s);
        phoneEdit.setEnabled(s);
        birthText.setEnabled(s);
        countryText.setEnabled(s);
        maleRadio.setEnabled(s);
        femaleRadio.setEnabled(s);
        etpictureView.setEnabled(s);
    }
}
