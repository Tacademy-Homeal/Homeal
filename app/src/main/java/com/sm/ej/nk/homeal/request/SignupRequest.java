package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class SignupRequest extends AbstractRequest<NetworkResultTemp> {

    Request request;

    public SignupRequest(Context context, String name, String birth, String phone, String introduce, String gender) {
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("users")
                .build();


        FormBody.Builder builder = new FormBody.Builder();
                builder.add("birth", birth)
                .add("introduce",introduce)
                .add("gender",gender)
                .add("country","korea")
                .add("phone",phone)
                .add("type","cooker")
                .build();

        RequestBody body = builder.build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return  new TypeToken<NetworkResultTemp>() {}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
