package com.beyond.shi.httputils_lib.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class AssetsUtils {

    private static final String ENCODING = "UTF-8";

    /**
     * 从assets中取出文件
     *
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     */
    public static InputStream getFileFromAssets(Context context, String fileName) throws IOException {
        AssetManager am = context.getAssets();
        return am.open(fileName);
    }

    /**
     * 从assets中读取出文本文件
     *
     * @param context
     * @param fileName
     * @return 文本文件的字符串形式
     */
    public static String getTextFromAssets(Context context, String fileName) {
        String result = null;
        try {
            InputStream in = getFileFromAssets(context, fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            result = new String(buffer, Charset.forName(ENCODING));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从assets中读取出文本文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getTextFromAssets2(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getFileFromAssets(context, fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析json字符串到数组内
     *
     * @param context
     * @param jsonName
     * @return
     */
    public static <T> ArrayList<T> parseJsonToArrayList(Context context, String jsonName) {
        String json = getTextFromAssets(context, jsonName);
        return JsonUtils.jsonToArrayList(json);
    }

    /**
     * 解析json字符串到数组内
     *
     * @param context
     * @param jsonName
     * @return
     */
    public static <T> List<T> parseJsonToList(Context context, String jsonName) {
        String json = getTextFromAssets(context, jsonName);
        return JsonUtils.jsonToList(json);
    }

    /**
     * 从assets中解析json字符串到对象内
     *
     * @param context
     * @param jsonName
     * @param clazz
     * @return
     */
    public static <T> T parseJsonToObject(Context context, String jsonName, Class<T> clazz) {
        String json = getTextFromAssets(context, jsonName);
        return JsonUtils.jsonToObject(json, clazz);
    }
}
