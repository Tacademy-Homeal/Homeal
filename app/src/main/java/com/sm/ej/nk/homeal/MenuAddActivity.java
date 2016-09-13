package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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

    @BindView(R.id.fab_menu_add_ok)
    android.support.design.widget.FloatingActionButton fab;

    @BindView(R.id.image_menu_add_activation)
    ImageView imageActivation;

    @BindView(R.id.text_menu_add_price)
    TextView textPrice;

    ArrayAdapter<String> mAdapter;

    private static int MODE;

    private static final int GET_IMAGE = 35;

    public static final int MODE_MENU=1;

    private CkDetailMenuData data;
    private String currency =null;
    private String activation= null;
    private String foodName= null;
    private String foodPrice= null;
    private String foodInfo= null;
    private File imageFile = null;

    private boolean isActivation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Glide.with(MenuAddActivity.this).load("https://pixabay.com/static/uploads/photo/2016/03/21/05/05/plus-1270001_960_720.png").into(image);
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
                    Intent intent = new Intent(MenuAddActivity.this, GalleryActivity.class);
                    intent.putExtra(GalleryActivity.INTENT_MODE, MODE_MENU);
                    startActivityForResult(intent, GET_IMAGE);
                }
            }
        });

        imageActivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MODE == CkMainActivity.MODE_MENU_INSERT || MODE == CkMainActivity.MODE_MENU_EDIT){
                    if(isActivation){
                        imageActivation.setImageResource(R.drawable.homeal_off_btn);
                        isActivation=false;
                    }else{
                        imageActivation.setImageResource(R.drawable.homeal_on_button);
                        isActivation = true;
                    }
                }
            }
        });
        editFoodPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(charSequence)){
                    textPrice.setVisibility(View.INVISIBLE);
                }else{
                    textPrice.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        fab.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                String imagePath = data.getStringExtra(GalleryActivity.IMAGE_PATH);
                imageFile = new File(imagePath);
                Glide.with(this).load(imageFile).into(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setMenuData(CkDetailMenuData data){
        Glide.with(this).load(data.getImage()).into(image);
        editFoodInfo.setText(data.introduce);
        editFoodName.setText(data.name);
        editFoodPrice.setText(""+data.price);
        if(data.activation==1){
            isActivation = true;
            imageActivation.setImageResource(R.drawable.homeal_on_button);
        }else{
            isActivation = false;
            imageActivation.setImageResource(R.drawable.homeal_off_btn);
        }
    }

    @Override
    public void onClick(View view) {
        if(MODE==CkMainActivity.MODE_MENU_INSERT){
            if(valueCheck()){
                CkMenuInsertRequest request = new CkMenuInsertRequest(MenuAddActivity.this, foodName, imageFile, foodPrice, foodInfo, currency, activation);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(MenuAddActivity.this, "매뉴 생성 완료", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                        Log.e("ssong", "error", e);
                    }
                });
            }
        }
        if(MODE==CkMainActivity.MODE_MENU_EDIT){
            if(valueDifCheck()){
                CkMenuEditRequest request = new CkMenuEditRequest(MenuAddActivity.this, data.id, foodName, imageFile, foodPrice, foodInfo, currency, activation);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(MenuAddActivity.this, "매뉴 편집 완료", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(MenuAddActivity.this, "매뉴 편집 실패", Toast.LENGTH_SHORT).show();
                        Log.e("ssong", "error", e);
                        Log.e("ssong", errorMessage);
                        Log.e("ssong", errorCode+"");
                    }
                });
            }
        }
    }

    private boolean valueCheck(){
        if(TextUtils.isEmpty(editFoodName.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            foodName = editFoodName.getText().toString();
        }
        if(TextUtils.isEmpty(editFoodInfo.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 설명을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            foodInfo = editFoodInfo.getText().toString();
        }
        if(TextUtils.isEmpty(editFoodPrice.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 가격을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            foodPrice = editFoodPrice.getText().toString();
        }
        if(isActivation){
            activation = "1";
        }else{
            activation="0";
        }

        activation = "1";

        if(imageFile==null){
            Toast.makeText(MenuAddActivity.this, "사진을 업로드해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean valueDifCheck(){
        boolean isChange=false;
        if(TextUtils.isEmpty(editFoodName.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            foodName = editFoodName.getText().toString();
        }
        if(TextUtils.isEmpty(editFoodInfo.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 설명을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            foodInfo = editFoodInfo.getText().toString();
        }
        if(TextUtils.isEmpty(editFoodPrice.getText().toString())){
            Toast.makeText(MenuAddActivity.this, "음식 가격을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            foodPrice = editFoodPrice.getText().toString();
        }
        if(isActivation){
            activation = "1";
        }else{
            activation="0";
        }
        currency = "1";

        if(data.getFoodName().equals(foodName)){
            foodName = null;
        }else{
            isChange = true;
        }

        if(data.introduce.equals(foodInfo)){
            foodInfo = null;
        }else{
            isChange = true;
        }

        if(data.price.equals(foodPrice)){
            foodPrice = null;
        }else{
            isChange = true;
        }

        if(data.activation == Integer.parseInt(activation)){
            activation = null;
        }else{
            isChange = true;
        }

        if(data.currency == Integer.parseInt(currency)){
            currency = null;
        }else{
            isChange = true;
        }

        return isChange;
    }
}
