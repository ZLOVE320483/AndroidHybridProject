package com.zlove.hybridproject.api;

import com.zlove.hybridproject.bean.param.LoginParams;
import com.zlove.hybridproject.bean.resp.Login;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public interface ApiService {

    @POST("account/v1/login")
    Observable<Login> getPersonalInfo(@Body LoginParams loginParams);
}
