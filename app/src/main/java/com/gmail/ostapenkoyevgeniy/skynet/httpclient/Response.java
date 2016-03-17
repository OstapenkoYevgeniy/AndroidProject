package com.gmail.ostapenkoyevgeniy.skynet.httpclient;

import android.util.Log;

import com.gmail.ostapenkoyevgeniy.skynet.support.AppConstant;

public class Response {
    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        Log.d(AppConstant.LOG_TAG_DEBUG, "responseCode: " + String.valueOf(responseCode));
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        Log.d(AppConstant.LOG_TAG_DEBUG, "responseMessage: " + responseMessage);
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        Log.d(AppConstant.LOG_TAG_DEBUG, "responseBody: " + responseBody);
    }

    public String getResponseBody() {
        return responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
