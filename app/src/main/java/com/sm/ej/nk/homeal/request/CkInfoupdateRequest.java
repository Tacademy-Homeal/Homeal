package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class CkInfoupdateRequest extends AbstractRequest<NetworkResultTemp> {

    Request request;
    Context context;

    public CkInfoupdateRequest(Context context, String name, String birth, String phone, String introduce, String address, String gender) {

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
