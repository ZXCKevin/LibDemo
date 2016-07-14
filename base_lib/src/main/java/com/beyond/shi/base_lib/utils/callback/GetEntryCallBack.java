package com.beyond.shi.base_lib.utils.callback;

import okhttp3.Call;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface GetEntryCallBack<T> {
    void getEntryResponse(T t);
    void getEntryFail(Call call, Exception e, int id);
}
