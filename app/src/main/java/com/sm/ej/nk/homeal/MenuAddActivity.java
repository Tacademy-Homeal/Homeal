package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAddActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_menu_add)
    Toolbar toolbar;

    private static int MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        ButterKnife.bind(this);

        Intent intent = getIntent();
//        MODE = intent.getIntExtra()

        setSupportActionBar(toolbar);

    }
}
