package com.zlove.hybridproject.hybrid;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zlove.hybridproject.ui.WebActivity;
import com.zlove.hybridproject.util.ToastUtils;


public class HybridWebViewClient extends WebViewClient {

    private WebActivity act;

    public HybridWebViewClient(WebActivity act) {
        this.act = act;
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e("MyTestUrl", url);
        // 更具对应的url 做相应的事件
        HybridHandler mHybridHandler = new HybridHandlerManager(act).createHybridHandler(HybridConstants.URL_TASK);
        if (mHybridHandler != null) {
            boolean is_handler = mHybridHandler.handlerTask(act, url);
            if (!is_handler) {
                ToastUtils.showLong("App没有处理");
                return false;
            }
            return true;
        } else {
            ToastUtils.showLong("App没有处理事件的--HybridHandler");
        }

        return false;
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);

        view.stopLoading();
        view.clearView();
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                act.getWebView().loadUrl("404");
            }
        });
    }


}