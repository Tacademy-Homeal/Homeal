package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sm.ej.nk.homeal.fragment.LoginFragment;
import com.sm.ej.nk.homeal.fragment.SingUpFragment;
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

    public void changeTos(int modenum){

//        this.modenum = modenum;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TOSFragment())
                .addToBackStack(null)
                .commit();
    }

    public void changeSingUp(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SingUpFragment())
                .addToBackStack(null)
                .commit();
    }

    public void moveMainActivity(){

//        switch (modenum){
//            case 0 :
//                Intent intent = new Intent(this, CkMainActivity.class);
//                startActivity(intent);
//
//                finish();
//                break;
//            case 1:
//                intent = new Intent(this, EtMainActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//        }
    }


}
