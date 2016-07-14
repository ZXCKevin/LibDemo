package com.beyond.shi.base_lib.utils.callback;

import android.support.annotation.Nullable;

import com.beyond.shi.base_lib.utils.request.BaseRequest;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangjinya on 2016/7/13.
 */
public interface DownFileCallBack {
    void startDown(BaseRequest request);
    void downFile(boolean isFromCache, File file, Request request, @Nullable Response response);
    void downCurrentProgress(long currentSize, long totalSize, float progress, long networkSpeed);
    void downFileFail(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e);
}
