package com.beyond.shi.base_lib.utils.callback;

import android.support.annotation.Nullable;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface GetStringCallBack {
    void getStringResponse(boolean isFromCache, String s, Request request, @Nullable Response response);
    void getStringFail(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e);
}
