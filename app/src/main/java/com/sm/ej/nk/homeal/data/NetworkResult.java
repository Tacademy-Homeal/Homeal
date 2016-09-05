package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class NetworkResult<T> {
    private T results;
    private int code;
    private String message;

    public T getResult() {
        return this.results;
    }
    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
