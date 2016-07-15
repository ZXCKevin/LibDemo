package com.beyond.shi.httputils_lib.callback;

import java.io.File;

import okhttp3.Call;

/**
 * Created by guojuan on 2016/7/15.
 */
public interface DownFileCallBack {
    void inProgress(float progress, long total, int id);
    void downFileErro(Call call, Exception e, int id);
    void downFileSuccess(File response, int id);
}
