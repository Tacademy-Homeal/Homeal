package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class NetworkResult<T> {
    private T result;
    private int code;

    public T getResult() {
        return this.result;
    }
    public int getCode() {
        return this.code;
    }
}
