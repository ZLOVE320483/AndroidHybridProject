package com.zlove.hybridproject.ui;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zlove.hybridproject.R;
import com.zlove.hybridproject.adapter.MyTextAdapter;
import com.zlove.hybridproject.api.SimpleMyCallBack;
import com.zlove.hybridproject.base.BaseActivity;
import com.zlove.hybridproject.base.BaseRecylerAdapter;
import com.zlove.hybridproject.bean.param.LoginParams;
import com.zlove.hybridproject.bean.resp.HttpExceptionBean;
import com.zlove.hybridproject.bean.resp.Login;
import com.zlove.hybridproject.util.ToastUtils;
import com.zlove.hybridproject.view.OnLoadMoreListener;
import com.zlove.hybridproject.view.OnRefreshListener;
import com.zlove.hybridproject.view.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by ZLOVE on 2016/11/17.
 */
public class WelcomeActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);

        initApi();

        /**
         * 网络请求
         */
        LoginParams mLoginParams = new LoginParams("18576400302", "123456");
        Subscription subscription =  apiWrapper.getUserInfo(mLoginParams)
                .subscribe(newMySubscriber(new SimpleMyCallBack<Login>() {
                    // 这个方法根据需要重写 之前已经toast了，如果toast了还要做其他的事情，就重写这个方法
                    @Override
                    public void onError(HttpExceptionBean mHttpExceptionBean) {
                        super.onError(mHttpExceptionBean);
                        showToast("zlove");
                    }
                    @Override
                    public void onNext(Login mLogin) {
                        showToast("登录成功"+mLogin.toString());
                    }
                }));
        compositeSubscription.add(subscription);
    }

    @Override
    public void initView() {

        /**
         * 刷新 recyclerView 点击事件
         */
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        recyclerView = (RecyclerView)findViewById(R.id.swipe_target);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        for(int i= 0;i<50; i++){
            list.add("ad");
        }
        MyTextAdapter adapter = new MyTextAdapter(this, list);
        adapter.setOnItemClickLitener(new BaseRecylerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showShort("点击了第--"+position+"--个");
            }
        });
        recyclerView.setAdapter(adapter);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)){
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
