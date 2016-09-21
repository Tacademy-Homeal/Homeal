package com.sm.ej.nk.homeal.request;

import android.content.Context;

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
public class CkMenuInsertRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;
    Context context;
    MediaType jpeg = MediaType.parse("image/jpeg");

    public CkMenuInsertRequest(Context context, String name, File image, String price, String introduce, String activation){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("menus")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder().
                addFormDataPart("name", name)
                .addFormDataPart("price", price)
                .addFormDataPart("introduce", introduce)
                .addFormDataPart("activation", activation);
        if(image!=null){
            builder.addFormDataPart("image",image.getName(), RequestBody.create(jpeg, image));
        }

        RequestBody body = builder.build();
        request = new Request.Builder()
                .url(url)
                .post(body)
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
