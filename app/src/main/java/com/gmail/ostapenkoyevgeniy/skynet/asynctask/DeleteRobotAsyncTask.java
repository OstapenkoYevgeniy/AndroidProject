package com.gmail.ostapenkoyevgeniy.skynet.asynctask;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gmail.ostapenkoyevgeniy.skynet.adapter.RobotAdapter;
import com.gmail.ostapenkoyevgeniy.skynet.dao.DaoException;
import com.gmail.ostapenkoyevgeniy.skynet.dao.RobotDao;
import com.gmail.ostapenkoyevgeniy.skynet.support.AppConstant;

public class DeleteRobotAsyncTask extends AbstractAsyncTask<Integer, Void, Void> {
    private RobotAdapter.RobotViewHolder robotViewHolder;

    public DeleteRobotAsyncTask(Context context, RobotAdapter.RobotViewHolder robotViewHolder) {
        super(context);
        this.robotViewHolder = robotViewHolder;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        RobotDao robotDao = new RobotDao();
        try {
            if (robotDao.deleteById(Integer.valueOf(params[0]))) {
                isSuccessful = true;
            }
        } catch (DaoException e) {
            Log.e(AppConstant.LOG_TAG_ERROR, e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (isSuccessful) {
            Toast.makeText(context, "Робот удален", Toast.LENGTH_LONG).show();
            robotViewHolder.removeAt(robotViewHolder.getPosition());
        } else {
            Toast.makeText(context, "Робот не удален! Ошибка!", Toast.LENGTH_LONG).show();
        }
    }
}
