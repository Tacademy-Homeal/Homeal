package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-20.
 */

public class ProfileRequest extends  AbstractRequest<NetworkResult<String>> {

    Request mRequest;

    public ProfileRequest(Context context){
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .build();

        mRequest = new Request.Builder()
                .url(url)
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
