package com.zlove.hybridproject.hybrid;


import com.zlove.hybridproject.hybrid.impl.CustomHandler;
import com.zlove.hybridproject.hybrid.impl.UrlHandler;
import com.zlove.hybridproject.ui.WebActivity;

/**
 *   HybridHandler 对象工厂类
 */

public class HybridHandlerManager {
    private WebActivity activity;

    public HybridHandlerManager(WebActivity activity) {
        this.activity = activity;
    }

    public HybridHandler createHybridHandler(String str) {
        // 先从 集合中取 如果没有去创建对象
        HybridHandler mHybridHandler = activity.getHybridHandlerMap().get(str);
        if (mHybridHandler != null) {
            return mHybridHandler;
        }

        //创建 url处理对象
        if (str.equals(HybridConstants.URL_TASK)) {
            UrlHandler urlHandler = new UrlHandler();
            activity.addHybridHandler(urlHandler.getHandlerName(),urlHandler);
            return urlHandler;
        }
        //创建 自定义消息处理对象
        if (str.equals(HybridConstants.CUSTOM_MESSAGE_TASK)) {
            CustomHandler mCustomHandler = new CustomHandler(activity);
            activity.addHybridHandler(mCustomHandler.getHandlerName(),mCustomHandler);
            return mCustomHandler;
        }

        return null;
    }




}
