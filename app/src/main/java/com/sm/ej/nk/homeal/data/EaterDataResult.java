package com.sm.ej.nk.homeal.data;

public class EaterDataResult {
    private EaterData[] result;
    private String message;

    public EaterData[] getResult() {
        return this.result;
    }
    public void setResult(EaterData[] result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
