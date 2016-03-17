package com.gmail.ostapenkoyevgeniy.skynet.asynctask;

import android.content.Context;
import android.os.AsyncTask;

public abstract class AbstractAsyncTask<Par, Pr, Res> extends AsyncTask<Par, Pr, Res> {
    protected Context context;
    protected boolean isSuccessful = false;

    public AbstractAsyncTask(Context context) {
        this.context = context;
    }
}
