package com.sm.ej.nk.homeal.manager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-26.
 */
public abstract class NetworkRequest<T> implements Callback {
    T result;
    int code;
    String errorMessage;
    Throwable exception;
    NetworkManager.OnResultListener<T> listener;

    public void setOnResultListener(NetworkManager.OnResultListener<T> listener) {
        this.listener = listener;
    }

    public abstract Request getRequest();
    protected abstract T parse(ResponseBody body) throws IOException;
    Call call;

    void process(OkHttpClient client) {
        Request request = getRequest();
        call = client.newCall(request);
        call.enqueue(this);
    }

    public T processSync(OkHttpClient client) throws IOException {
        Request request = getRequest();
        call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            T result = parse(response.body());
            return result;
        } else {
            throw new IOException("code : " + response.code() + ",message : " + response.message());
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        sendError(-1, e.getMessage(), e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            try {
                sendSuccess(parse(response.body()));
            } catch (IOException e) {
                sendError(-1, e.getMessage(), e);
            }
        } else {
            sendError(response.code(), response.message(), null);
        }
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

    public boolean isCancel() {
        if (call == null) return false;
        return call.isCanceled();
    }

    private void sendSuccess(T result) {
        this.result = result;
        NetworkManager.getInstance().sendSuccess(this);
    }

    void sendSuccess() {
        if (listener != null) {
            listener.onSuccess(this, result);
        }
    }

    protected void sendError(int code, String errorMessage, Throwable exception) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.exception = exception;
        NetworkManager.getInstance().sendFail(this);
    }

    void sendFail() {
        if (listener != null) {
            listener.onFail(this, code, errorMessage, exception);
        }
    }


}