package com.gmail.ostapenkoyevgeniy.skynet.httpclient;

import android.util.Log;

import com.gmail.ostapenkoyevgeniy.skynet.support.AppConstant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_PUT = "PUT";

    private String urlAPI;
    private String method;
    private String data;
    private boolean isExecute = false;

    public HttpClient(String url, String method) {
        this.urlAPI = url;
        this.method = method;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Response execute() throws HttpClientException {
        if (!isExecute) {
            switch (method) {
                case METHOD_GET:
                    return executeGet();
                case METHOD_POST:
                    return executePost();
                case METHOD_DELETE:
                    return executeDelete();
                case METHOD_PUT:
                    return executePut();
            }
            isExecute = true;
        } else {
            throw new UnsupportedOperationException("Запрос уже выполнялся!");
        }
        return null;
    }

    private Response executePut() {
        Response response = new Response();
        HttpURLConnection conn;
        URL objUrl;
        try {
            objUrl = new URL(urlAPI);
            conn = (HttpURLConnection) objUrl.openConnection();
            conn.setRequestMethod(method);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(data.getBytes());
            wr.close();

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            response.setResponseBody(br.readLine());
            conn.disconnect();
            br.close();
        } catch (Exception e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return response;
    }

    private Response executeDelete() {
        Response response = new Response();
        HttpURLConnection conn;
        URL objUrl;
        try {
            objUrl = new URL(urlAPI);
            conn = (HttpURLConnection) objUrl.openConnection();
            conn.setRequestMethod(method);

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            response.setResponseBody(br.readLine());
            conn.disconnect();
            br.close();
        } catch (Exception e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return response;
    }

    private Response executePost() {
        Response response = new Response();
        HttpURLConnection conn;
        URL objUrl;
        try {
            objUrl = new URL(urlAPI);
            conn = (HttpURLConnection) objUrl.openConnection();
            conn.setRequestMethod(method);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(data.getBytes());
            wr.close();

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            response.setResponseBody(br.readLine());
            conn.disconnect();
            br.close();
        } catch (Exception e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return response;
    }

    private Response executeGet() {
        Response response = new Response();
        HttpURLConnection conn;
        URL objUrl;
        try {
            objUrl = new URL(urlAPI);
            conn = (HttpURLConnection) objUrl.openConnection();
            conn.setRequestMethod(method);

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            response.setResponseBody(br.readLine());
            conn.disconnect();
            br.close();
        } catch (Exception e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return response;
    }
}
