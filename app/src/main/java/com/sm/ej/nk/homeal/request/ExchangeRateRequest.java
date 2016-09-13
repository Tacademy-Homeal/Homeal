package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.QueryResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-13.
 */
public class ExchangeRateRequest extends AbstractRequest<QueryResult> {

    Request request;

    private static final String URL =  "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%3D%22KRWUSD%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    public ExchangeRateRequest(Context context, String target){
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http");
        builder.host(URL);

        HttpUrl url = builder.build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<QueryResult>() {}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
