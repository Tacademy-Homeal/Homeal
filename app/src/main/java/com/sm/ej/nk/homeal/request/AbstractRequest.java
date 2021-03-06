package com.sm.ej.nk.homeal.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkRequest;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-26.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {
    private static final String HTTP_URL = "ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com";
    private static final String HTTPS_URL =  "ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com";

    protected HttpUrl.Builder getBaseHttpsUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host(HTTPS_URL);
        builder.port(443);

        return builder;
    }

    protected HttpUrl.Builder getBaseHttpUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http");
        builder.host(HTTP_URL);
        builder.port(80);

        return builder;
    }

    @Override
    protected T parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);

        Log.e("ssong", text);

        if (temp.getCode() == 1) {
            T result = gson.fromJson(text, getType());
            return result;
        } else if (temp.getCode() == 0) {
            Type type = new TypeToken<NetworkResult<String>>(){}.getType();
            NetworkResult<String> result = gson.fromJson(text, type);
            throw new IOException(result.getMessage());
        } else {
            T result = gson.fromJson(text, getType(temp.getCode()));
            return result;
        }
    }

    protected Type getType(int code) {
        return getType();
    }

    protected abstract Type getType();


}
