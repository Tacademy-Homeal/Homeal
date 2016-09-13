package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class FacebookLoginRequest extends  AbstractRequest<NetworkResult<String>> {

    Request mRequest;

    public FacebookLoginRequest(Context context, String token, String regId){
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("auth")
                .addPathSegment("facebook")
                .addPathSegment("token")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("access_token", token)
                .add("registration_token", regId)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
