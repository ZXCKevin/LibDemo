package com.beyond.shi.httputils_lib.callback;

import okhttp3.Call;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface GetStringCallBack {
    void getStringResponse(String response, int id);

    void getStringFail(Call call, Exception e, int id);
}
