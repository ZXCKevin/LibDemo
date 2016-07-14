package com.beyond.shi.base_lib.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.beyond.shi.base_lib.utils.cache.CacheMode;
import com.beyond.shi.base_lib.utils.callback.BitmapCallback;
import com.beyond.shi.base_lib.utils.callback.DownFileCallBack;
import com.beyond.shi.base_lib.utils.callback.GetEntryCallBack;
import com.beyond.shi.base_lib.utils.callback.GetStringCallBack;
import com.beyond.shi.base_lib.utils.callback.LoadImageCallBack;
import com.beyond.shi.base_lib.utils.callback.PostFileCallBack;
import com.beyond.shi.base_lib.utils.callback.PostStringCallBack;
import com.beyond.shi.base_lib.utils.callback.StringCallback;
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
     * 将文件作为请求体，发送到服务器
     *
     * @param context
     * @param file             File对象，上传文件的路径
     * @param url              上传的网络地址
     * @param postFileCallBack 接口回调
     */
    public void postFileResponse(Context context, File file, String url, final PostFileCallBack postFileCallBack) {
        if (!file.exists()) {
            return;
        }
        OkHttpUtils
                .post(url)
                .
                .tag(context)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        postFileCallBack.postFileCurrent(progress, total);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        postFileCallBack.postFileResponse(response);
                    }
                });
    }


    /**
     * 根据自己的需求去自定义Callback，例如希望回调自己设置的对象
     *
     * @param context
     * @param url              请求地址
     * @param getEntryCallBack 回调接口
     */
    public void getEntryResponse(Context context, String url, final GetEntryCallBack getEntryCallBack) {
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(context)
                .build()//
                .execute(new Callback<Object>() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        Object obj = new Gson().fromJson(string, Object.class);
                        return obj;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getEntryCallBack.getEntryFail(call, e, id);
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        getEntryCallBack.getEntryResponse(response);
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
    public void downFileResponse(Context context, String url, String destFileDir, String destFileName
            , final DownFileCallBack downFileCallBack) {
        OkHttpUtils//
                .get()//
                .url(url)//
                .tag(context)
                .build()//
                .execute(new FileCallBack(destFileDir, destFileName) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        downFileCallBack.downCurrentProgress(progress, total);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        downFileCallBack.downFileFail(call, e, id);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        downFileCallBack.downFile(response);
                    }
                });
    }

    //显示图片，可选该方法，也可用BitmapUtils中的来加载图片
    public void loadGetBitmapResponse(Context context, String url, final LoadImageCallBack loadImageCallBack) {
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(context)
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadImageCallBack.loadImageFail(call, e, id);
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        loadImageCallBack.loadImage(response);
                    }
                });
    }

    //取消网络请求，确保Context为同一个,tag为同一个activity
    public void cancelHttpResponse(Context context) {
        OkHttpUtils.getInstance().cancelTag(context);
    }
}
