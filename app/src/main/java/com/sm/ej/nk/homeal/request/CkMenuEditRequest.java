package com.sm.ej.nk.homeal.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class CkMenuEditRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;
    Context context;
    MediaType jpeg = MediaType.parse("image/jpeg");

    public CkMenuEditRequest(Context context, String menuId, String name, File image, String price, String introduce, String currency, String activation){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("menus")
                .addPathSegment(menuId)
                .build();

        Log.e("ssong url", url.toString());
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if(name!=null){
            builder.addFormDataPart("name", name);
            Log.e("ssong", name);
        }
        if(price!=null){
            builder.addFormDataPart("price", price);
            Log.e("ssong", price);
        }
        if(introduce!=null){
            builder.addFormDataPart("introduce", introduce);
            Log.e("ssong", introduce);
        }
        if(currency!=null){
            builder.addFormDataPart("currency", currency);
            Log.e("ssong", currency);
        }
        if(activation!=null){
            builder.addFormDataPart("activation", activation);
            Log.e("ssong", activation);
        }
        if(image!=null){
            builder.addFormDataPart("image", image.getName(), RequestBody.create(jpeg, image));
            Log.e("ssong", "imagedsdsds");
        }

        RequestBody body = builder.build();


        request = new Request.Builder()
                .url(url)
                .put(body)
                .tag(context)
                .build();
    }


    @Override
    protected Type getType() {
        return new TypeToken<NetworkResultTemp>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
