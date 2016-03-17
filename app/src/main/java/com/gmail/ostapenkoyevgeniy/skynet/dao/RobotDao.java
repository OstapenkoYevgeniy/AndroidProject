package com.gmail.ostapenkoyevgeniy.skynet.dao;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;
import com.gmail.ostapenkoyevgeniy.skynet.httpclient.HttpClient;
import com.gmail.ostapenkoyevgeniy.skynet.httpclient.HttpClientException;
import com.gmail.ostapenkoyevgeniy.skynet.httpclient.Response;
import com.gmail.ostapenkoyevgeniy.skynet.support.AppConstant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RobotDao implements Dao<Robot, Integer> {
    public static final String COLUMN_NAME = "name";

    private static final String URL_ADD = "http://frontend.test.pleaple.com/api/robots";
    private static final String URL_GET_ALL = "http://frontend.test.pleaple.com/api/robots";
    private static final String URL_DELETE = "http://frontend.test.pleaple.com/api/robots/";
    private static final String URL_UPDATE = "http://frontend.test.pleaple.com/api/robots/";
    private static final String URL_GET_BY_ID = "http://frontend.test.pleaple.com/api/robots/";
    private static final String URL_GET_BY_NAME = "http://frontend.test.pleaple.com/api/robots/search/";

    @Override
    public boolean insert(Robot object) throws DaoException {
        String json = ObjectToJson(object);
        HttpClient httpClient = new HttpClient(URL_ADD, HttpClient.METHOD_POST);
        httpClient.setData(json);
        try {
            Response response = httpClient.execute();
            if (response.getResponseCode() != 201) {
                return false;
            }
        } catch (HttpClientException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return true;
    }

    @Override
    public boolean update(Robot object) throws DaoException {
        String json = ObjectToJson(object);
        HttpClient httpClient = new HttpClient(URL_UPDATE + object.getId(), HttpClient.METHOD_PUT);
        httpClient.setData(json);
        try {
            Response response = httpClient.execute();
            if (response.getResponseCode() != 200) {
                return false;
            }
        } catch (HttpClientException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return true;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        HttpClient httpClient = new HttpClient(URL_DELETE + id, HttpClient.METHOD_DELETE);
        try {
            Response response = httpClient.execute();
            if (response.getResponseCode() != 200) {
                return false;
            }
        } catch (HttpClientException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return true;
    }

    @Override
    public Robot getById(Integer key) throws DaoException {
        HttpClient httpClient = new HttpClient(URL_GET_BY_ID + key, HttpClient.METHOD_GET);
        List<Robot> result;
        try {
            Response response = httpClient.execute();
            if (response.getResponseCode() == 200) {
                result = JsonToRobots(response.getResponseBody());
                if (result.size() >= 1) {
                    return result.get(0);
                }
            }
        } catch (HttpClientException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return null;
    }

    @Override
    public Robot getByColumn(String column, String value) throws DaoException {
        HttpClient httpClient = new HttpClient(URL_GET_BY_NAME + value, HttpClient.METHOD_GET);
        List<Robot> result;
        try {
            Response response = httpClient.execute();
            Log.d(AppConstant.LOG_TAG_DEBUG, String.valueOf(response.getResponseCode()));
            if (response.getResponseCode() == 200) {
                result = JsonToRobots(response.getResponseBody());
                if (result.size() >= 1) {
                    return result.get(0);
                }
            }
        } catch (HttpClientException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return null;
    }

    @Override
    public List<Robot> getAll() throws DaoException {
        HttpClient httpClient = new HttpClient(URL_GET_ALL, HttpClient.METHOD_GET);
        try {
            Response response = httpClient.execute();
            Log.d(AppConstant.LOG_TAG_DEBUG, String.valueOf(response.getResponseCode()));
            if (response.getResponseCode() == 200) {
                return JsonToRobots(response.getResponseBody());
            }
        } catch (HttpClientException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return null;
    }

    /**
     * Very bad code, sorry!
     */
    private List<Robot> JsonToRobots(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Robot[] robots = new Robot[1];

        try {
            if (json.startsWith("{\"status\":\"FOUND\",\"data\":")) {
                json = json.substring(json.indexOf("data\":") + 6, json.length() - 1);
                robots[0] = mapper.readValue(json, Robot.class);
            } else {
                robots = mapper.readValue(json, Robot[].class);
            }
        } catch (IOException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }

        if (robots != null) {
            List<Robot> result = new ArrayList<>();
            for (Robot robot : robots) {
                result.add(robot);
            }
            return result;
        }
        return null;
    }

    private String ObjectToJson(Robot robot) throws DaoException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(outputStream, robot);
        } catch (IOException e) {
            throw new DaoException(e);
        }
        return new String(outputStream.toByteArray());
    }
}
