package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.data.CkHomeItemData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAddActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_menu_add)
    Toolbar toolbar;

    @BindView(R.id.image_menu_add)
    ImageView image;

    @BindView(R.id.edit_menu_add_foodinfo)
    EditText editFoodInfo;

    @BindView(R.id.edit_menu_add_foodname)
    EditText editFoodName;

    @BindView(R.id.edit_menu_add_foodprice)
    EditText editFoodPrice;

    @BindView(R.id.spinner_menu_add)
    Spinner spinner;

    @BindView(R.id.text_menu_add_money)
    TextView textView;
//sdsd
    @BindView(R.id.fab_menu_add_ok)
    android.support.design.widget.FloatingActionButton fab;

    ArrayAdapter<String> mAdapter;

    private static int MODE;
    private CkHomeItemData data;

    private static final int GET_IMAGE = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE,-1);
        if(MODE == CkMainActivity.MODE_MENU_EDIT){
            data = (CkHomeItemData)intent.getSerializableExtra(CkMainActivity.INTENT_MENU_DATA);
            setMenuData(data);
        }else if(MODE == CkMainActivity.MODE_MENU_INSERT){

        }else if(MODE == CkMainActivity.MODE_MENU_SHOW){
            data = (CkHomeItemData)intent.getSerializableExtra(CkMainActivity.INTENT_MENU_DATA);
            setMenuData(data);
            editFoodName.setEnabled(false);
            editFoodPrice.setEnabled(false);
            editFoodInfo.setEnabled(false);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, GET_IMAGE);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.money));
        mAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                Glide.with(this).load(uri).into(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setMenuData(CkHomeItemData data){
        Glide.with(this).load(data.foodimage).into(image);
        editFoodInfo.setText(data.foodInfo);
        editFoodName.setText(data.foodName);
        editFoodPrice.setText(data.foodPrice);
    }
}
