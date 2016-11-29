package com.zlove.hybridproject.base;

import com.google.gson.Gson;
import com.zlove.hybridproject.api.Api;
import com.zlove.hybridproject.api.ApiWrapper;
import com.zlove.hybridproject.api.SimpleMyCallBack;
import com.zlove.hybridproject.bean.resp.HttpExceptionBean;
import com.zlove.hybridproject.util.ToastUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public class BaseCommonPresenter<T extends BaseView> {

    protected ApiWrapper apiWrapper;
    protected CompositeSubscription compositeSubscription;

    public T view;

    public BaseCommonPresenter(T view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
        apiWrapper = new ApiWrapper();
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     * @param onNext
     * @param <E>
     * @return
     */
    protected <E> Subscriber newMySybscriber(final SimpleMyCallBack onNext) {
        return new Subscriber<E>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.hideLoading();
                }
                onNext.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    if (view != null) {
                        ToastUtils.showShort(exception.message);
                    }
                } else if (e instanceof HttpException) {
                    if (e instanceof HttpException) {
                        ResponseBody body = ((HttpException) e).response().errorBody();
                        try {
                            String json = body.string();
                            Gson gson = new Gson();
                            HttpExceptionBean mHttpExceptionBean = gson.fromJson(json, HttpExceptionBean.class);
                            if (mHttpExceptionBean != null && mHttpExceptionBean.getMessage() != null) {
                                ToastUtils.showShort(mHttpExceptionBean.getMessage());
                                onNext.onError(mHttpExceptionBean);
                            }
                        } catch (IOException IOe) {
                            IOe.printStackTrace();
                        }
                    }
                }
                if (view != null) {
                    view.hideLoading();
                }
            }

            @Override
            public void onNext(Object o) {
                if (!compositeSubscription.isUnsubscribed()) {
                    onNext.onNext(o);
                }
            }
        };
    }

    /**
     * 解绑 CompositeSubscription
     */
    public void unsubscribe() {
        if(compositeSubscription != null){
            compositeSubscription.unsubscribe();
        }
    }
}
