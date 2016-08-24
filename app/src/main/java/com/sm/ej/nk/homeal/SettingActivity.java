package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_ck_setting_advice)
    public void onSettingadvice(){
        Intent intent = new Intent(SettingActivity.this, AdviceActivity.class);
        startActivity(intent);
    }

}
