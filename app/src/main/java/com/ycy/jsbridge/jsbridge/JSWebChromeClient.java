package com.ycy.jsbridge.jsbridge;

import android.net.Uri;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class JSWebChromeClient extends WebChromeClient {
    //自定义JS 请求协议：myjsbridge:///request?class=指定调用的类名&method=指定调用的方法名&params=指定的参数&callId=指定的请求ID
    private static final String JS_REQUEST_PREFIX = JSBridge.MY_JS_BRIDGE + ":///request?";
    private static final String JS_REQUEST_CLASS_KEY = "class";
    private static final String JS_REQUEST_METHOD_KEY = "method";
    private static final String JS_REQUEST_PARAMETERS_KEY = "params";
    private static final String JS_REQUEST_CALL_ID_KEY = "callId";

    private final JSBridge jsBridge;

    JSWebChromeClient(JSBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (message.startsWith(JS_REQUEST_PREFIX)) {
            if (this.jsBridge == null) {
                result.cancel();
                return true;
            }
            parseJSProtocol(message);
            result.cancel();
            return true;
        }

        return super.onJsAlert(view, url, message, result);
    }

    /**
     * 解析JS协议
     *
     * @param message: myjsbridge:///request?class=指定调用的类名&method=指定调用的方法名&params=指定的参数&callId=指定的请求ID
     */
    private void parseJSProtocol(String message) {
        String[] tokens = message.substring(JS_REQUEST_PREFIX.length()).split("&");
        String target = null;
        String method = null;
        String params = null;
        long callID = -1;

        for (String token : tokens) {
            String[] pair = token.split("=");

            if (pair.length != 2) {
                continue;
            }

            try {
                String key = pair[0];
                String value = Uri.decode(pair[1]);

                if (JS_REQUEST_CLASS_KEY.equals(key)) {
                    target = value;
                } else if (JS_REQUEST_METHOD_KEY.equals(key)) {
                    method = value;
                } else if (JS_REQUEST_PARAMETERS_KEY.equals(key)) {
                    params = value;
                } else if (JS_REQUEST_CALL_ID_KEY.equals(key)) {
                    callID = Long.parseLong(value);
                }
            } catch (Exception e) {
                // Ignores.
            }
        }

        if (target != null && method != null && callID >= 0) {
            this.jsBridge.requestAndroid(target, method, params, callID);
        }
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        //TODO:这里可以显示进度
        super.onProgressChanged(view, newProgress);
    }
}