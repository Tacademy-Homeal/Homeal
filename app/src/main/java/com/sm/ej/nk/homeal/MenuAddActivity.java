package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkMenuEditRequest;
import com.sm.ej.nk.homeal.request.CkMenuInsertRequest;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAddActivity extends AppCompatActivity implements View.OnClickListener{

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

    @BindView(R.id.fab_menu_add_ok)
    android.support.design.widget.FloatingActionButton fab;

    ArrayAdapter<String> mAdapter;

    private static int MODE;
    private CkDetailMenuData data;

    private static final int GET_IMAGE = 35;

    private String currency;
    private String activation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE,-1);
        if(MODE == CkMainActivity.MODE_MENU_EDIT){
            fab.show();
            data = (CkDetailMenuData)intent.getSerializableExtra(CkMainActivity.INTENT_MENU_DATA);
            setMenuData(data);
        }else if(MODE == CkMainActivity.MODE_MENU_INSERT){
            fab.show();


        }else if(MODE == CkMainActivity.MODE_MENU_SHOW){
            fab.hide();
            data = (CkDetailMenuData)intent.getSerializableExtra(CkMainActivity.INTENT_MENU_DATA);
            setMenuData(data);
            editFoodName.setEnabled(false);
            editFoodPrice.setEnabled(false);
            editFoodInfo.setEnabled(false);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MODE == CkMainActivity.MODE_MENU_INSERT || MODE == CkMainActivity.MODE_MENU_EDIT){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, GET_IMAGE);
                }
            }
        });

        fab.setOnClickListener(this);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.money));
        mAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(mAdapter);

    }

    File imageFile = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                Glide.with(this).load(uri).into(image);
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if(c.moveToNext()){
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    imageFile = new File(path);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setMenuData(CkDetailMenuData data){
        Glide.with(this).load(data.getImage()).into(image);
        editFoodInfo.setText(data.introduce);
        editFoodName.setText(data.name);
        editFoodPrice.setText(""+data.price);
    }

    @Override
    public void onClick(View view) {
        if(MODE==CkMainActivity.MODE_MENU_INSERT){
            if(valueCheck()){
                CkMenuInsertRequest request = new CkMenuInsertRequest(MenuAddActivity.this, editFoodName.getText().toString(), imageFile, editFoodPrice.getText().toString(), editFoodInfo.getText().toString(), "1", "1");
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(MenuAddActivity.this, "매뉴 생성 완료", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                        Log.e("ssong", "error", e);
                    }
                });
            }
        }
        if(MODE==CkMainActivity.MODE_MENU_EDIT){
            if(valueCheck()){
                CkMenuEditRequest request = new CkMenuEditRequest(MenuAddActivity.this, data.id, editFoodName.getText().toString(), imageFile, editFoodPrice.getText().toString(), editFoodInfo.getText().toString(), "1", "1");
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(MenuAddActivity.this, "매뉴 편집 완료", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {

                    }
                });
            }

        }
    }

    private boolean valueCheck(){
        if(TextUtils.isEmpty(editFoodName.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(editFoodInfo.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 설명을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(editFoodPrice.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 가격을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(imageFile==null){
            Toast.makeText(MenuAddActivity.this, "사진을 업로드해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
