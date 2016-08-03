package com.ycy.jsbridge;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.ycy.jsbridge.jsbridge.JSBridge;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webview = (WebView) findViewById(R.id.webview);

        //1.实例化JSBridge，配置WebView
        JSBridge jsBridge = new JSBridge(this, webview);
        jsBridge.configWebView();

        //2.WebView 加载网页资源
        webview.loadUrl("file:///android_asset/demo.html");
    }
}
