package com.ycy.jsbridge.jsapi;

import android.widget.Toast;

import com.ycy.jsbridge.jsbridge.JSBridge;

import org.json.JSONObject;

/**
 * Created by YCY.
 */
public class JSUIControl {

    public void showToast(JSBridge jsBridge, Long callId, JSONObject requestParams) {
        String content = requestParams.optString("content");
        Toast.makeText(jsBridge.getActivity(), content, Toast.LENGTH_LONG).show();
        //回调JS
        jsBridge.reportSuccess(callId);
    }
}
