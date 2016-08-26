package com.sm.ej.nk.homeal;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtZzimActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_et_home_ft)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    EtHomeFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_zzim);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("찜 목 록");

        mAdapter = new EtHomeFragmentAdapter();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        initData();
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

    private void initData() {
        for (int i = 0; i < 10 ; i++) {
            mAdapter.add(ContextCompat.getDrawable(this, R.drawable.ic_launcher));
        }
    }
}
