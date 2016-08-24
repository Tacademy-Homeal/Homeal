package com.sm.ej.nk.homeal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReserveRequestActivity extends AppCompatActivity {

    @OnClick(R.id.btn_reserve_request)
    public void onSenddialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("확인 버튼", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("최소 버튼", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(" 아직 결제기능 이용 불가능");
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_request);
        ButterKnife.bind(this);
    }
}
