package com.zlove.hybridproject.hybrid;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.zlove.hybridproject.ui.WebActivity;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public class HybridChromeClient extends WebChromeClient {
    private WebActivity webActivity;

    public HybridChromeClient(WebActivity webActivity) {
        this.webActivity = webActivity;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }
}
