package com.zlove.hybridproject.hybrid;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.zlove.hybridproject.ui.WebActivity;
import com.zlove.hybridproject.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anzhuo002 on 2016/6/27.
 */

public class HybridInterface {

    private WebActivity mActivity;
    //处理事件的名字
    private final String EVEN_NAME = "evenName";
    //具体处理事件的内容，此字段不使用，只是打印
    private final String MSG = "msg";
    public HybridInterface(WebActivity mActivity) {
        this.mActivity = mActivity;
    }
    /**
     * html5 调用native App 统一的方法
     *
     * @param string
     */
    @JavascriptInterface
    public void nativeHanderTask(final String string) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("nativeHandlerTask", string + "");
                String evenName = "";
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    evenName = jsonObject.getString(EVEN_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HybridHandler mHybridHandler = new HybridHandlerManager(mActivity).createHybridHandler(evenName);
                if (mHybridHandler != null) {
                    boolean is_handler =  mHybridHandler.handlerTask(mActivity,string);
                    if( !is_handler){
                        ToastUtils.showShort("App没有处理");
                    }
                }else{
                    ToastUtils.showShort("App没有处理事件的--HybridHandler");
                }

            }
        });

    }
}
