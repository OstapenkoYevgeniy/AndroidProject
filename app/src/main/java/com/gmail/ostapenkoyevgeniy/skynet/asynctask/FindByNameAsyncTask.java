package com.gmail.ostapenkoyevgeniy.skynet.asynctask;

import android.content.Context;
import android.util.Log;

import com.gmail.ostapenkoyevgeniy.skynet.AllRobotActivity;
import com.gmail.ostapenkoyevgeniy.skynet.dao.Dao;
import com.gmail.ostapenkoyevgeniy.skynet.dao.DaoException;
import com.gmail.ostapenkoyevgeniy.skynet.dao.RobotDao;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;
import com.gmail.ostapenkoyevgeniy.skynet.support.AppConstant;

import java.util.ArrayList;
import java.util.List;

public class FindByNameAsyncTask extends AbstractAsyncTask<String, Void, Void> {
    private List<Robot> result = new ArrayList<>();

    public FindByNameAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected Void doInBackground(String... params) {
        Log.d(AppConstant.LOG_TAG_DEBUG, "AddRobotAsyncTask -> doInBackground(Robot...) start.");
        Dao<Robot, Integer> robotDao = new RobotDao();
        try {
            result.add(robotDao.getByColumn(RobotDao.COLUMN_NAME, params[0]));
        } catch (DaoException e) {
            Log.d(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        AllRobotActivity allRobotActivity = (AllRobotActivity) context;
        allRobotActivity.addRobots(result);
    }
}
