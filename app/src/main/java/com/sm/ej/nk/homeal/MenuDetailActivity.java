package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuDetailActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_menu_detail)
    Toolbar toolbar;
    @BindView(R.id.Detailimageview)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.food));



    }

    @OnClick(R.id.Detailimageview)
    public void onDetailClick(){
        Toast.makeText(this,"Photo click",Toast.LENGTH_LONG).show();
    }



}
