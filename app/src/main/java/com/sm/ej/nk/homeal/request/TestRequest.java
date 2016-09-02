package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class TestRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;
    public TestRequest(Context context, String type){
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("auth/local/login")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("email", type)
                .add("password", "1234")
                .build();


        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
