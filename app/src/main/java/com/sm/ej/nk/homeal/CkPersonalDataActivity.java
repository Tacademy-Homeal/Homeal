package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.sm.ej.nk.homeal.data.CookerData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkInfoRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CkPersonalDataActivity extends AppCompatActivity {

    ArrayAdapter<String> countryAdapter;

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_ck_name)
    EditText nameEdit;

    @BindView(R.id.edit_ck_age)
    EditText ageEdit;

    @BindView(R.id.text_ck_address)
    TextView addressText;

    @BindView(R.id.edit_ck_introduce)
    EditText introduceEdit;

    @BindView(R.id.edit_ck_phone)
    EditText phoneEdit;

    @BindView(R.id.spinner_ck_month)
    Spinner monthSpinner;

    @BindView(R.id.spinner_ck_day)
    Spinner daySpinner;

    @BindView(R.id.spinner_ck_year)
    Spinner yearSpinner;

    @BindView(R.id.spinner_ck_country)
    Spinner countrySpinner;

    @BindView(R.id.radio_ck_male)
    RadioButton maleRadio;

    @BindView(R.id.radio_ck_female)
    RadioButton femaleRadio;

    @BindView(R.id.btn_ck_changefinish)
    Button btnChangeFinish;

    @BindView(R.id.image_ck_picture)
    ImageView ckpictureView;

    private int GET_IMAGE = 2;

    @OnClick(R.id.image_ck_picture)
    public void onckGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
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
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == ADDRESS_SEARCH) {
            if (resultCode == RESULT_OK) {
                String address = intent.getStringExtra("address");
                addressText.setText(address);
            }
        }
        if (requestCode == GET_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = intent.getData();
                ckpictureView.setImageURI(uri);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_personal_data);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("개 인 정 보");

        countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.country));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        countrySpinner.setAdapter(countryAdapter);

        ArrayList<String> monthList = new ArrayList<>();
        for (int month = 1; month < 13; month++) {
            monthList.add(String.valueOf(month));
        }
        ArrayAdapter<String> monthAdatper = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monthList);
        monthSpinner.setAdapter(monthAdatper);

        ArrayList<String> dayList = new ArrayList<>();
        for (int day = 1; day < 32; day++) {
            dayList.add(String.valueOf(day));
        }
        ArrayAdapter<String> dayAdatper = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayList);
        daySpinner.setAdapter(dayAdatper);

        ArrayList<String> yearList = new ArrayList<>();
        for (int year = 1930; year < 2031; year++) {
            yearList.add(String.valueOf(year));
        }
        ArrayAdapter<String> yearAdatper = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, yearList);
        yearSpinner.setAdapter(yearAdatper);

        isPersonalData(false);
        btnChangeFinish.setVisibility(View.GONE);

       CkInfoRequest request = new CkInfoRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<CookerData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CookerData>> request, NetworkResult<CookerData> result) {

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CookerData>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @OnClick(R.id.btn_personal_change)
    public void onPersonalChanged() {
        isPersonalData(true);
        btnChangeFinish.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_ck_changefinish)
    public void onChangefinish() {
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
        ageEdit.setEnabled(s);
        addressText.setEnabled(s);
        introduceEdit.setEnabled(s);
        phoneEdit.setEnabled(s);
        monthSpinner.setEnabled(s);
        daySpinner.setEnabled(s);
        yearSpinner.setEnabled(s);
        countrySpinner.setEnabled(s);
        maleRadio.setEnabled(s);
        femaleRadio.setEnabled(s);
        ckpictureView.setEnabled(s);
    }
}
