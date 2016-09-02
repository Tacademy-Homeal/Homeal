package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkRequest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-26.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {
    private static final String HTTP_URL = "ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com:80";
    private static final String HTTPS_URL =  "ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com:443";

    protected HttpUrl.Builder getBaseHttpsUrlBuilder(Context context) {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host(HTTPS_URL);
        return builder;
    }

    protected HttpUrl.Builder getBaseHttpUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http");
        builder.host(HTTP_URL);
        return builder;
    }

    @Override
    protected T parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);
        if (temp.getCode() == 1) {
            T result = gson.fromJson(text, getType());
            return result;
        } else if (temp.getCode() == 2) {
            Type type = new TypeToken<NetworkResult<String>>(){}.getType();
            NetworkResult<String> result = gson.fromJson(text, type);
            throw new IOException(result.getResult());
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
