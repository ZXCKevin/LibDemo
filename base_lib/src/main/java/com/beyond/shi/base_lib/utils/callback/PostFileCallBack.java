package com.beyond.shi.base_lib.utils.callback;

import okhttp3.Call;

/**
 * Created by wangjinyya on 2016/7/13.
 */
public interface PostFileCallBack {
    void postFileResponse(String response);
    void postFileCurrent(float currentProgress,float totalProgress);
    void postFileFail(Call call, Exception e, int id);
}
