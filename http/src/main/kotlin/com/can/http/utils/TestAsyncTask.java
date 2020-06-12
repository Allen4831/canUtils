package com.can.http.utils;

import android.os.AsyncTask;

import com.can.mvp.bean.responseBean.BaseResponseBean;

/**
 * Created by CAN on 2020/5/29.
 */
public class TestAsyncTask extends AsyncTask<String, Integer, BaseResponseBean> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(BaseResponseBean aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected BaseResponseBean doInBackground(String... voids) {
        return null;
    }
}
