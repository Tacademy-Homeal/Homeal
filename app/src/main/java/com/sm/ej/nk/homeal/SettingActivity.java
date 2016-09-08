package com.sm.ej.nk.homeal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_currecy_setting)
    Spinner currencySpinner;

    @BindView(R.id.spinner_language_setting)
    Spinner languageSpinner;

    ArrayAdapter<String> currencyAdapter;
    ArrayAdapter<String> languageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.SettingActivity_appbar));

        currencyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currency));
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        currencySpinner.setAdapter(currencyAdapter);

        languageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.language));
        languageAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        languageSpinner.setAdapter(languageAdapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(SettingActivity.this, "Korean", Toast.LENGTH_SHORT).show();

                        Locale locale = new Locale("ko");
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.locale = locale;
                        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                        break;

                    case 1:
                        Toast.makeText(SettingActivity.this, "English", Toast.LENGTH_SHORT).show();
                        locale = new Locale("en");
                        Locale.setDefault(locale);
                        configuration = new Configuration();
                        configuration.locale = locale;
                        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SettingActivity.this, "언어를 설정해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.linear_top)
    public void onSettingFaq() {
        Intent intent = new Intent(SettingActivity.this, AdviceActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.linear_bottom)
    public void onLeavemembership() {
        showDialog();
    }

    @OnClick(R.id.btn_ck_logout)
    public void onLogout() {
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.leavemembership_dialog));
        builder.show();
    }
}
