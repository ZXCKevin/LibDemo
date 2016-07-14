package com.beyond.shi.base_lib.utils.callback;

import java.io.File;

import okhttp3.Call;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface DownFileCallBack {
    void downFile(File file);
    void downCurrentProgress(float progress,float totalProgress);
    void downFileFail(Call call, Exception e, int id);
}
