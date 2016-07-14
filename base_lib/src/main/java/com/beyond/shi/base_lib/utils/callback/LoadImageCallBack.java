package com.beyond.shi.base_lib.utils.callback;

import android.graphics.Bitmap;

import okhttp3.Call;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface LoadImageCallBack {
    void loadImage(Bitmap bitmap);
    void loadImageFail(Call call, Exception e, int id);
}
