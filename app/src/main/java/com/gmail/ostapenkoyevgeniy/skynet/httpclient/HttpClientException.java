package com.gmail.ostapenkoyevgeniy.skynet.httpclient;

public class HttpClientException extends Exception {
    public HttpClientException() {
        super();
    }

    public HttpClientException(String detailMessage) {
        super(detailMessage);
    }

    public HttpClientException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpClientException(Throwable throwable) {
        super(throwable);
    }
}
