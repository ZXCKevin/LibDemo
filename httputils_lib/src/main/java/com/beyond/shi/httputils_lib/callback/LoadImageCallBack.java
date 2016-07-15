package com.beyond.shi.httputils_lib.callback;

import android.graphics.Bitmap;

import okhttp3.Call;

/**
 * Created by guojuan on 2016/7/15.
 */
public interface LoadImageCallBack {
    void loadImage(Bitmap response, int id);
    void loadImageFail(Call call, Exception e, int id);
}
