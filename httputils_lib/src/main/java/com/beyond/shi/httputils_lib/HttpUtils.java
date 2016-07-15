package com.beyond.shi.httputils_lib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.beyond.shi.httputils_lib.callback.BitmapCallback;
import com.beyond.shi.httputils_lib.callback.DownFileCallBack;
import com.beyond.shi.httputils_lib.callback.FileCallBack;
import com.beyond.shi.httputils_lib.callback.GetStringCallBack;
import com.beyond.shi.httputils_lib.callback.LoadImageCallBack;
import com.beyond.shi.httputils_lib.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;

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
     * 需要在Application中，初始化HttpUtils
     *
     * @param application
     */
    public void initHttpUtils(Application application, int cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cache(new Cache(application.getCacheDir(), cache))
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * get请求得到字符串
     *
     * @param context
     * @param url
     * @param getStringCallBack
     */
    public void getStringResponse(Context context, String url, final GetStringCallBack getStringCallBack) {
        OkHttpUtils.get()
                .tag(context)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getStringCallBack.getStringFail(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        getStringCallBack.getStringResponse(response, id);
                    }
                });
    }


    /**
     * post请求，提交一个Gson字符串到服务器端
     *
     * @param context
     * @param url      网址
     * @param bodyJson 请求体json字符串
     */
    public void postStringResponse(Context context, String url, String bodyJson, final GetStringCallBack getStringCallBack) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(bodyJson)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getStringCallBack.getStringFail(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        getStringCallBack.getStringResponse(response, id);
                    }
                });

    }

    /**
     * post请求map集合
     *
     * @param context
     * @param url
     * @param map     map集合
     */
    public void postStringResponse(Context context, String url, Map<String, String> map, final GetStringCallBack getStringCallBack) {
        if (map != null) {
            String bodyJson = new JSONObject(map).toString();
            OkHttpUtils
                    .postString()
                    .url(url)
                    .content(bodyJson)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            getStringCallBack.getStringFail(call, e, id);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            getStringCallBack.getStringResponse(response, id);
                        }
                    });
        }
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
        OkHttpUtils//
                .get()//
                .tag(context)
                .url(url)//
                .build()//
                .execute(new FileCallBack(destFileDir, destFileName) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        downFileCallBack.inProgress(progress, total, id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        downFileCallBack.downFileErro(call, e, id);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        downFileCallBack.downFileSuccess(response, id);
                    }
                });
    }


    /**
     * 不建议使用该方法加载图片，建议使用BitmapUtils来加载图片
     *
     * @param context
     * @param url
     * @param loadImageCallBack
     */
    public void loadGetBitmapResponse(Activity context, String url, final LoadImageCallBack loadImageCallBack) {
        OkHttpUtils
                .get()//
                .tag(context)
                .url(url)//
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadImageCallBack.loadImageFail(call, e, id);
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        loadImageCallBack.loadImage(response, id);
                    }
                });
    }

    /**
     * 将文件作为请求体，发送到服务器。
     *
     * @param context
     * @param url
     * @param file
     * @param getStringCallBack
     */
    public void postUpFileResponse(Context context, String url, File file, final GetStringCallBack getStringCallBack) {
        OkHttpUtils
                .postFile()
                .tag(context)
                .url(url)
                .file(file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getStringCallBack.getStringFail(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        getStringCallBack.getStringResponse(response, id);
                    }
                });
    }

    /**
     * 上传单个表单文件
     *
     * @param context
     * @param nameKey
     * @param fileName
     * @param file
     * @param params
     */
    public void postFormFileResponse(Context context,
                                     String nameKey, String fileName, File file,
                                     Map<String, String> params, Map<String, String> headers, final GetStringCallBack getStringCallBack) {
        OkHttpUtils.post()
                .tag(context)
                .addFile(nameKey, fileName, file)
                .params(params)
                .headers(headers)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getStringCallBack.getStringFail(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        getStringCallBack.getStringResponse(response, id);
                    }
                });
    }

    /**
     * 取消网络请求
     *
     * @param context
     */
    public void cancelResponse(Context context) {
        OkHttpUtils.getInstance().cancelTag(context);
    }
}
