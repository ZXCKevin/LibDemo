package com.beyond.shi.base_lib.utils.callback;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface LoadImageCallBack {
    void loadImage(boolean isFromCache, Bitmap bitmap, Request request, @Nullable Response responseBitmap);
    void loadImageFail(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e);
}
