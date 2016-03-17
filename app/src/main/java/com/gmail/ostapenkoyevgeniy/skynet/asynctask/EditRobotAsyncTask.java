package com.gmail.ostapenkoyevgeniy.skynet.asynctask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gmail.ostapenkoyevgeniy.skynet.adapter.RobotAdapter;
import com.gmail.ostapenkoyevgeniy.skynet.dao.Dao;
import com.gmail.ostapenkoyevgeniy.skynet.dao.DaoException;
import com.gmail.ostapenkoyevgeniy.skynet.dao.RobotDao;
import com.gmail.ostapenkoyevgeniy.skynet.entity.Robot;
import com.gmail.ostapenkoyevgeniy.skynet.support.AppConstant;

public class EditRobotAsyncTask extends AbstractAsyncTask<Robot, Void, Void> {
    private RecyclerView rv;
    private Robot robot;

    public EditRobotAsyncTask(Context context, View rv) {
        super(context);
        this.rv = (RecyclerView) rv;
    }

    @Override
    protected Void doInBackground(Robot... params) {
        robot = params[0];
        Dao<Robot, Integer> robotDao = new RobotDao();
        try {
            if (robotDao.update(robot)) {
                isSuccessful = true;
            }
        } catch (DaoException e) {
            Log.d(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (isSuccessful) {
            Toast.makeText(context, "Робот успешно изменен!", Toast.LENGTH_LONG).show();
            RobotAdapter adapter = (RobotAdapter) rv.getAdapter();
            adapter.robotViewHolder.update(adapter.robotViewHolder.getPosition(), robot);
        } else {
            Toast.makeText(context, "Произошла ошибка, попробуйте позднее!", Toast.LENGTH_LONG).show();
        }
    }
}
