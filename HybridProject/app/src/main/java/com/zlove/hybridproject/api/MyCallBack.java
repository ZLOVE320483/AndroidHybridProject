package com.zlove.hybridproject.api;

import com.zlove.hybridproject.bean.resp.HttpExceptionBean;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public interface MyCallBack<T> {
    void onCompleted();
    void onError(HttpExceptionBean httpExceptionBean);
    void onNext(T t);
}
