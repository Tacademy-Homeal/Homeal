package com.sm.ej.nk.homeal.data;

/**
 * CrAd by Tacademy on 2016-09-02.
 */
public class CookerDataResult {
    private CookerData[] result;
    private String message;

    public CookerData[] getResult() {
        return this.result;
    }
    public void setResult(CookerData[] result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
