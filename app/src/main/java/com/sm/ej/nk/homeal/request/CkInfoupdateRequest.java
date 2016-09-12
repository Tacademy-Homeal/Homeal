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
 * Created by Tacademy on 2016-09-09.
 */
public class CkInfoupdateRequest extends AbstractRequest<NetworkResultTemp> {
    MediaType jpeg = MediaType.parse("image/jpeg");
    Request request;
    Context context;

    public CkInfoupdateRequest(Context context, String name, String birth, String phone, String introduce, String address, String gender, File file) {

        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("cookers")
                .addPathSegment("me")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .addFormDataPart("name", name)
                .addFormDataPart("birth", birth)
                .addFormDataPart("phone", phone)
                .addFormDataPart("introduce", introduce)
                .addFormDataPart("address", address)
                .addFormDataPart("gender", gender);
        if (file != null){
            builder.addFormDataPart("myFile", file.getName(), RequestBody.create(jpeg, file));
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
        return new TypeToken<NetworkResultTemp>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}