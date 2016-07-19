package com.beyond.shi.httputils_lib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.beyond.shi.httputils_lib.callback.BitmapCallback;
import com.beyond.shi.httputils_lib.callback.FileCallBack;
import com.beyond.shi.httputils_lib.callback.StringCallback;
import com.beyond.shi.httputils_lib.callback.TCallBack;
import com.beyond.shi.httputils_lib.https.HttpsUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
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
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cache(new Cache(application.getCacheDir(), cache))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)//支持所有的https://协议
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * get请求得到字符串
     *
     * @param context
     * @param url
     * @param callback
     */
    public void getStringResponse(Context context, String url, StringCallback callback) {
        OkHttpUtils.get()
                .tag(context)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * get请求得到实体类对象，需输入泛型
     *
     * @param context
     * @param url
     * @param callBack
     */
    public void getStringResponse(Context context, String url, TCallBack callBack) {
        OkHttpUtils.get()
                .tag(context)
                .url(url)
                .build()
                .execute(callBack);
    }

    /**
     * post请求
     *
     * @param context
     * @param url
     * @param params
     * @param callback
     */
    public void postResponse(Context context, String url, Map<String, String> params, StringCallback callback) {
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .build()
                .execute(callback);
    }

    /**
     * post请求得到实体类，需输入泛型
     *
     * @param context
     * @param url
     * @param params
     * @param callBack
     */
    public void postResponse(Context context, String url, Map<String, String> params, TCallBack callBack) {
        OkHttpUtils.post().url(url).params(params).build().execute(callBack);
    }

    /**
     * post请求，提交一个Gson字符串到服务器端
     *
     * @param context
     * @param url      网址
     * @param bodyJson 请求体json字符串
     */
    public void postStringResponse(Context context, String url, String bodyJson, StringCallback callback) {
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(bodyJson)
                .build()
                .execute(callback);

    }

    /**
     * post请求提交一个map集合到服务器
     *
     * @param context
     * @param url
     * @param map     map集合
     */
    public void postStringResponse(Context context, String url, Map<String, String> map, StringCallback callback) {
        if (map != null) {
            String bodyJson = new JSONObject(map).toString();
            OkHttpUtils
                    .postString()
                    .url(url)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(bodyJson)
                    .build()
                    .execute(callback);
        }
    }

    /**
     * @param context
     * @param url              网址
     * @param destFileDir      下载的路径 如：Environment.getExternalStorageDirectory().getAbsolutePath()
     * @param destFileName     下载文件的文件命名 如："gson-2.2.1.jar"
     * @param callBack
     */
    public void downFileResponse(Context context, String url, final String destFileDir, String destFileName
            ,FileCallBack callBack) {
        OkHttpUtils//
                .get()//
                .tag(context)
                .url(url)//
                .build()//
                .execute(callBack);
    }


    /**
     * 不建议使用该方法加载图片，建议使用BitmapUtils来加载图片
     *
     * @param context
     * @param url
     * @param callback
     */
    public void loadGetBitmapResponse(Activity context, String url, BitmapCallback callback) {
        OkHttpUtils
                .get()//
                .tag(context)
                .url(url)//
                .build()//
                .execute(callback);
    }

    /**
     * 将文件作为请求体，发送到服务器。
     *
     * @param context
     * @param url
     * @param file
     * @param callback
     */
    public void postUpFileResponse(Context context, String url, File file, StringCallback callback) {
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .postFile()
                .tag(context)
                .url(url)
                .file(file)
                .build()
                .execute(callback);
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
                                     Map<String, String> params, Map<String, String> headers, StringCallback callback) {
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post()
                .tag(context)
                .addFile(nameKey, fileName, file)
                .params(params)
                .headers(headers)
                .build()
                .execute(callback);
    }

    /**
     * 上传多个个表单文件
     *
     * @param context
     * @param key
     * @param files
     * @param params
     */
    public void postFormFileResponse(Context context,
                                     String key, Map<String, File> files,
                                     Map<String, String> params, Map<String, String> headers, StringCallback callback) {
        OkHttpUtils.post()
                .tag(context)
                .files(key, files)
                .params(params)
                .headers(headers)
                .build()
                .execute(callback);
    }
    //TODO HEAD、DELETE、PUT、PATCH 如需要这些中请求方式，可定义静态方法调用

    /**
     * 取消网络请求
     *
     * @param context
     */
    public void cancelResponse(Context context) {
        OkHttpUtils.getInstance().cancelTag(context);
    }
}
