package com.ycy.jsbridge.jsapi;

import com.ycy.jsbridge.jsbridge.JSBridge;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YCY.
 */
public class JSBizDemo {

    public void doBiz(JSBridge jsBridge, Long callId, JSONObject requestParams) throws JSONException {
        String content = requestParams.optString("content");
        JSONObject callbackParams = new JSONObject();
        if ("ok".equals(content)) {
            callbackParams.put("bizId", 1);
            jsBridge.reportSuccess(callId, callbackParams);
        } else {
            callbackParams.put("bizErrorId", 11);
            jsBridge.reportError(callId, callbackParams);
        }
    }
}
