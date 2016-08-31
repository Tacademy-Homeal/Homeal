package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EtPersonalDataActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_et_name)
    EditText nameEdit;

    @BindView(R.id.edit_et_age)
    EditText ageEdit;

    @BindView(R.id.edit_et_introduce)
    EditText introduceEdit;

    @BindView(R.id.edit_et_phone)
    EditText phoneEdit;

    @BindView(R.id.spinner_et_month)
    Spinner monthSpinner;

    @BindView(R.id.spinner_et_day)
    Spinner daySpinner;

    @BindView(R.id.spinner_et_year)
    Spinner yearSpinner;

    @BindView(R.id.spinner_et_country)
    Spinner countrySpinner;

    @BindView(R.id.radio_et_male)
    RadioButton maleRadio;

    @BindView(R.id.radio_et_female)
    RadioButton femaleRadio;

    @BindView(R.id.btn_et_changefinish)
    Button btnChangeFinish;

    @BindView(R.id.image_et_picture)
    ImageButton etpictureView;

    ArrayAdapter<String> countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_personal_data);
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
    }

    @OnClick(R.id.btn_personal_change)
    public void onPersonalChanged() {
        isPersonalData(true);
        btnChangeFinish.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_et_changefinish)
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
        introduceEdit.setEnabled(s);
        phoneEdit.setEnabled(s);
        monthSpinner.setEnabled(s);
        daySpinner.setEnabled(s);
        yearSpinner.setEnabled(s);
        countrySpinner.setEnabled(s);
        maleRadio.setEnabled(s);
        femaleRadio.setEnabled(s);
        etpictureView.setEnabled(s);
    }
}
