package com.zlove.hybridproject.api;

import com.zlove.hybridproject.bean.param.LoginParams;
import com.zlove.hybridproject.bean.resp.Login;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public class ApiWrapper extends Api {

    public Observable<Login> getUserInfo(LoginParams params) {
        return applySchedulers(getApiService().getPersonalInfo(params));
    }
}
