package com.ycy.jsbridge.jsbridge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JSWebViewClient extends WebViewClient {
    private JSBridge jsBridge;

    JSWebViewClient(JSBridge jsBridge) {
        super();
        this.jsBridge = jsBridge;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO:这里可以拦截url自定义动作，比如打电话，发邮件
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                jsBridge.getActivity().startActivity(intent);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        //TODO:这里可以对错误进行处理，比如出错后显示错误页面
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        //TODO:这里可以对页面刚加载时处理，比如显示进度条
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //TODO:这里可以对页面加载完成时处理，比如隐藏进度条
        super.onPageFinished(view, url);
    }
}