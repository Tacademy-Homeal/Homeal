package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CkPersonalDataActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_ck_name)
    EditText nameEdit;

    @BindView(R.id.edit_ck_age)
    EditText ageEdit;

    @BindView(R.id.edit_ck_address)
    EditText addressEdit;

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
    ImageButton ckpictureView;

    @OnClick(R.id.edit_ck_address)
    public void onAddressEdit(){
        startActivity(new Intent(CkPersonalDataActivity.this, AddressEditActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_personal_data);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("개 인 정 보");

        isPersonalData(false);
        btnChangeFinish.setVisibility(View.GONE);

        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        addressEdit.setText(address);
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
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void isPersonalData(boolean s) {
        nameEdit.setEnabled(s);
        ageEdit.setEnabled(s);
        addressEdit.setEnabled(s);
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
