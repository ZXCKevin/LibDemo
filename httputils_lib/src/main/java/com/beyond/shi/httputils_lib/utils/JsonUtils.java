package com.beyond.shi.httputils_lib.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/21.
 */
public class JsonUtils {

    /**
     * 对象转字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 字符串转成对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * json字符串转List
     *
     * @param json
     * @return
     */
    public static <T> List<T> jsonToList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * JSON字符串转 ArrayList<?>
     *
     * @param json
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<ArrayList<T>>() {
        }.getType());
    }

    /**
     * JSON字符串转Map
     *
     * @param json
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<K, V>>() {
        }.getType());
    }

}
