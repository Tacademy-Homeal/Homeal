package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sm.ej.nk.homeal.fragment.LoginFragment;
import com.sm.ej.nk.homeal.fragment.SignUpFragment;
import com.sm.ej.nk.homeal.fragment.TOSFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //create LoginFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    public void changeTos(){

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TOSFragment())
                .addToBackStack("Tos")
                .commit();
    }

    public void changeSingUp(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .addToBackStack("SingUp")
                .commit();
    }

    public void backtoTos(){
        getSupportFragmentManager()
                .popBackStack("Tos", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    public void backSingUp(){
        getSupportFragmentManager()
                .popBackStack("SingUp", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

}



