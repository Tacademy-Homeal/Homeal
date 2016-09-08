package com.sm.ej.nk.homeal.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class CkMenuDeleteRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;
    public CkMenuDeleteRequest(Context context, String menuId){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("menus")
                .addPathSegment(menuId)
                .build();

        Log.e("ssong url ", url.toString());

        request = new Request.Builder()
                .url(url)
                .delete()
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
