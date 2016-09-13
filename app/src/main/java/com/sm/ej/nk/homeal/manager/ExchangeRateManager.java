package com.sm.ej.nk.homeal.manager;

import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.data.QueryResult;
import com.sm.ej.nk.homeal.request.ExchangeRateRequest;

/**
 * Created by Tacademy on 2016-09-13.
 */
public class ExchangeRateManager {
    private static ExchangeRateManager instance;
    String rate;

    public static ExchangeRateManager getInstance(){
        if(instance == null){
            instance = new ExchangeRateManager();
        }
        return instance;
    }

    private ExchangeRateManager(){
        ExchangeRateRequest request = new ExchangeRateRequest(HomealApplication.getContext(), null);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<QueryResult>() {
            @Override
            public void onSuccess(NetworkRequest<QueryResult> request, QueryResult result) {
                rate = result.getQuery().getResults().getRate().getRate();
            }

            @Override
            public void onFail(NetworkRequest<QueryResult> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    public String getRate(){
        return rate;
    }
}
