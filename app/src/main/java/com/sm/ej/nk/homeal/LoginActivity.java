package com.sm.ej.nk.homeal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

        checkPermission();
    }
    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("눌러");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermission();
                    }
                });

                builder.create().show();
                return;
            }
            requestPermission();
        }
    }

    private void finishNoPermission() {
        Toast.makeText(this, "눌러", Toast.LENGTH_SHORT).show();
        finish();
    }

    private static final int RC_PERMISSION=1;
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RC_PERMISSION){
            if(grantResults != null && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                finishNoPermission();
            }
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

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

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



