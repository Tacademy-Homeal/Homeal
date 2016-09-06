package com.sm.ej.nk.homeal.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ReserveData;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class EtReserveRequest extends AbstractRequest<NetworkResult<List<ReserveData>>>{

    Request mRequest;
    public EtReserveRequest(Context context) {

        //URL Setting
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("reservations")
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

/*      In post
        RequestBody body = new FormBody.Builder()
               .add("키","벨류")
                .build();*//*

        mRequest = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();*/
        Log.i("uri",""+url);
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<ReserveData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
