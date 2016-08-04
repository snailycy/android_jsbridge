##JSBridge
对js与android交互进行的封装，没有js注入漏洞，安全可靠，兼容android所有系统版本

##用法
    //1.实例化JSBridge，配置WebView
    JSBridge jsBridge = new JSBridge(this, webview);
    jsBridge.configWebView();

    //2.WebView 加载网页资源
    webview.loadUrl("file:///android_asset/demo.html");

##项目说明
1.js使用alert方式调用android接口：

    var json = JSON.stringify({"content":"js call native!"});
    alert("myjsbridge:///request?class=JSUIControl&method=showToast&params="+
     encodeURIComponent(json)+"&callId=1");

2.android调用js：

	//String callbackJS = "javascript:myjsbridge.callbackFromNative(1,0,{});"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
	    //4.4及以上使用evaluateJavascript
	    this.mWebView.evaluateJavascript(callbackJS, null);
    } else {
    	this.mWebView.loadUrl(callbackJS);
    }
