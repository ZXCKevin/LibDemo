package com.beyond.shi.base_lib.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.beyond.shi.base_lib.utils.cache.CacheMode;
import com.beyond.shi.base_lib.utils.callback.BitmapCallback;
import com.beyond.shi.base_lib.utils.callback.DownFileCallBack;
import com.beyond.shi.base_lib.utils.callback.FileCallback;
import com.beyond.shi.base_lib.utils.callback.GetStringCallBack;
import com.beyond.shi.base_lib.utils.callback.LoadImageCallBack;
import com.beyond.shi.base_lib.utils.callback.PostStringCallBack;
import com.beyond.shi.base_lib.utils.callback.StringCallback;
import com.beyond.shi.base_lib.utils.request.BaseRequest;
import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangjinya on 2016/7/13.
 */
public class HttpUtils {
    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        return Nested.instance;
    }

    //在第一次被引用时被加载
    static class Nested {
        private static HttpUtils instance = new HttpUtils();
    }

    /**
     * 初始化HttpUtils
     *
     * @param application
     */
    public void initHttpUtils(Application application) {
        OkHttpUtils.init(application);
    }

    /**
     * get请求得到字符串
     *
     * @param context
     * @param url
     * @param getStringCallBack
     */
    public void getStringResponse(Context context, String url, final GetStringCallBack getStringCallBack) {
        OkHttpUtils.get(url)
                .tag(context)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .headers("Connection", "close")           //如果对于部分自签名的https访问不成功，需要加上该控制头
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new StringCallback() {
                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        getStringCallBack.getStringFail(isFromCache, call, response, e);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        getStringCallBack.getStringResponse(isFromCache, s, request, response);
                    }
                });
    }


    /**
     * post请求，提交一个Gson字符串到服务器端
     *
     * @param context
     * @param url                网址
     * @param bodyJson           请求体json字符串
     * @param postStringCallBack 回调接口
     */
    public void postStringResponse(Context context, String url, String bodyJson, final PostStringCallBack postStringCallBack) {
        OkHttpUtils
                .post(url)
                .postJson(bodyJson)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .headers("Connection", "close")           //如果对于部分自签名的https访问不成功，需要加上该控制头
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .tag(context)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new StringCallback() {
                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                    }

                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {

                    }
                });
    }

    /**
     * post请求
     *
     * @param context
     * @param url
     * @param map                map集合
     * @param postStringCallBack
     */
    public void postStringResponse(Context context, String url, Map<String, String> map, final PostStringCallBack postStringCallBack) {
        if (map != null) {
            String bodyJson = new Gson().toJson(map);
            OkHttpUtils
                    .post(url)
                    .postJson(bodyJson)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .headers("Connection", "close")           //如果对于部分自签名的https访问不成功，需要加上该控制头
                    .headers("header1", "headerValue1")//
                    .params("param1", "paramValue1")//
                    .tag(context)
                    .execute(new StringCallback() {
                        @Override
                        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                        }

                        @Override
                        public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {

                        }
                    });
        }
    }

    /**
     * 像服务器发送一个字符串
     *
     * @param context
     * @param text
     * @param url
     */
    public void postFileResponse(Context context, String text, String url, final GetStringCallBack getStringCallBack) {
        OkHttpUtils.post(url)
                .tag(context)
                .postString(text)
                .execute(new StringCallback() {
                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        getStringCallBack.getStringFail(isFromCache, call, response, e);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        getStringCallBack.getStringResponse(isFromCache, s, request, response);
                    }
                });

    }

    /**
     * @param context
     * @param url              网址
     * @param destFileDir      下载的路径 如：Environment.getExternalStorageDirectory().getAbsolutePath()
     * @param destFileName     下载文件的文件命名 如："gson-2.2.1.jar"
     * @param downFileCallBack
     */
    public void downFileResponse(Context context, String url, final String destFileDir, String destFileName
            , final DownFileCallBack downFileCallBack) {
        OkHttpUtils.get(url)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")
                .execute(new FileCallback(destFileDir, destFileName) {
                    @Override
                    public void onBefore(BaseRequest request) {
                        //开始下载
                        downFileCallBack.startDown(request);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                        //下载完成
                        downFileCallBack.downFile(isFromCache, file, request, response);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //下载进度
                        downFileCallBack.downCurrentProgress(currentSize, totalSize, progress, networkSpeed);
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        //下载出错
                        downFileCallBack.downFileFail(isFromCache, call, response, e);
                    }
                });
    }

    //显示图片，可选该方法，也可用BitmapUtils中的来加载图片
    public void loadGetBitmapResponse(Context context, String url, final LoadImageCallBack loadImageCallBack) {
        OkHttpUtils
                .get(url)//
                .tag(context)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                    }

                    @Override
                    public void onResponse(boolean isFromCache, Bitmap bitmap, Request request, @Nullable Response response) {

                    }
                });
    }

    //取消网络请求，确保Context为同一个,tag为同一个activity
    public void cancelHttpResponse(Context context) {
        OkHttpUtils.getInstance().cancelTag(context);
    }
}
