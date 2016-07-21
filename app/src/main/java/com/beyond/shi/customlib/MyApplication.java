package com.beyond.shi.customlib;

import android.app.Application;

import com.beyond.shi.httputils_lib.HttpUtils;

/**
 * Created by guojuan on 2016/7/13.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.getInstance().initHttpUtils(this);
    }
}
