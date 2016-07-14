package com.beyond.shi.base_lib.utils.callback;

import okhttp3.Call;

/**
 * Created by jinya on 2016/7/13.
 */
public interface PostStringCallBack {
    void postStringResponse(String response);
    void postStringFail(Call call, Exception e, int id);
}
