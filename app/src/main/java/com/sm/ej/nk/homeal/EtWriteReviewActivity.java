package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.fragment.EtReserveFragment;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.WriteReservationRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EtWriteReviewActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.rating_taste)
    RatingBar rating_taste;

    @BindView(R.id.rating_price)
    RatingBar rating_price;

    @BindView(R.id.rating_clean)
    RatingBar rating_clean;

    @BindView(R.id.rating_kindness)
    RatingBar rating_kindess;

    @BindView(R.id.edit_after_write)
    EditText editText;

    private String cookerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_write_review);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        cookerId = intent.getStringExtra(EtReserveFragment.USER);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("후 기 작 성");
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

    @OnClick(R.id.btn_et_review_finish)
    public void onReviewfinish() {

        int clean = rating_clean.getNumStars();
        int taste = rating_taste.getNumStars();
        int kindness = rating_kindess.getNumStars();
        int price = rating_price.getNumStars();
        String content = editText.getText().toString();
        WriteReservationRequest request = new WriteReservationRequest(this,cookerId,content,taste,price,clean,kindness);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getApplicationContext(),"전송에 실패 하였습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
