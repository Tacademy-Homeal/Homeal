package com.sm.ej.nk.homeal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.sm.ej.nk.homeal.fragment.LoginFragment;
import com.sm.ej.nk.homeal.fragment.SignUpFragment;
import com.sm.ej.nk.homeal.fragment.TOSFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_container, new LoginFragment())
                    .commit();
        }

    }

    public void changeTos(){

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, new TOSFragment())
                .addToBackStack("Tos")
                .commit();
    }

    public void changeSingUp(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, new SignUpFragment())
                .addToBackStack("SingUp")
                .commit();
    }

    public void moveMainActivity(){

        if(HomealApplication.isCooker())
        {
            Intent intent = new Intent(this, CkMainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this, EtMainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);



        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.login_dialog));
        builder.show();

    }

    @Override
    public void onBackPressed() {
        showDialog();
    }
}



