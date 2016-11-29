package com.zlove.hybridproject.api;

import com.zlove.hybridproject.bean.resp.HttpExceptionBean;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public abstract class SimpleMyCallBack<T> implements MyCallBack<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(HttpExceptionBean httpExceptionBean) {
    }
}
