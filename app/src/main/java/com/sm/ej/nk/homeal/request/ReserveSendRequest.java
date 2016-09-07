package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class ReserveSendRequest extends AbstractRequest<NetworkResultTemp>{
    Request request;
    public ReserveSendRequest(Context context, String cookerid, String scheduleid, List<CkDetailMenuData> menus, String pax){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("reservations")
                .build();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("cooker", cookerid)
                .add("schedule", scheduleid)
                .add("pax", pax);
        for(int i=0; i<menus.size(); i++){
            builder.add("menu",menus.get(i).id);
        }
        RequestBody body = builder.build();

        request = new Request.Builder()
                .url(url)
                .post(body)
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
