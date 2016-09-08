package com.sm.ej.nk.homeal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static Integer str=0;

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_currecy_setting)
    Spinner currencySpinner;

    @BindView(R.id.spinner_language_setting)
    Spinner languageSpinner;

    ArrayAdapter<String> currencyAdapter;
    ArrayAdapter<String> languageAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        languageSpinner.setSelection(str);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                str = languageSpinner.getSelectedItemPosition();
                switch (position) {
                    case 0:
                        SharedPreferences sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
//                        str =languageSpinner.getSelectedItemPosition();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Spinner languageSpinner = (Spinner) findViewById(R.id.spinner_language_setting);
                        editor.putInt("ko", languageSpinner.getSelectedItemPosition());
                        editor.commit();
                        Locale locale = new Locale("ko");
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.locale = locale;
                        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                        break;

                    case 1:
                        sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
//                        str =languageSpinner.getSelectedItemPosition();
                        editor = sharedPreferences.edit();
                        languageSpinner = (Spinner) findViewById(R.id.spinner_language_setting);
                        editor.putInt("en", languageSpinner.getSelectedItemPosition());
                        editor.commit();
                        locale = new Locale("en");
                        Locale.setDefault(locale);
                        configuration = new Configuration();
                        configuration.locale = locale;
                        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                        break;
                }
                finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SettingActivity.this, "언어를 설정해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        SharedPreferences sharedPreferences = this.getSharedPreferences("Language", Context.MODE_PRIVATE);
//        String pine = sharedPreferences.getString("language", "");
//        String languageToLoad = pine;
//        Locale locale = new Locale(languageToLoad);//Set Selected Locale
//        Locale.setDefault(locale);//set new locale as default
//        Configuration config = new Configuration();//get Configuration
//        config.locale = locale;//set config locale as selected locale
//        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
//    }

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
        builder.setPositiveButton(""+getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(""+getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.leavemembership_dialog));
        builder.show();
    }
}
