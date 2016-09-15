package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.ChatMessage;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class MessageListRequest extends AbstractRequest<NetworkResult<List<ChatMessage>>> {
    Request mRequest;
    public MessageListRequest(Context context, Date date) {
        String dateString = com.sm.ej.nk.homeal.Utils.convertTimeToString(date);

        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("messagelist")
                .addQueryParameter("lastDate",dateString)
                .build();


    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<ChatMessage>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
