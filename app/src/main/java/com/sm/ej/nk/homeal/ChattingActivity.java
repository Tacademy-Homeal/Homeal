package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChattingActivity extends AppCompatActivity {

    @BindView(R.id.rv_chattinglist)
    RecyclerView rv_chatting;

    @BindView(R.id.toobar_chatting)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ButterKnife.bind(this);

        //set Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
