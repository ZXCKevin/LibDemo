package com.beyond.shi.httputils_lib.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * 新建的callback文件，用来得到网络数据返回实体类
 * @Package com.beyond.shi.httputils_lib.callback
 * @Title
 * @Description
 * @author WangJinya
 * @Time 2016/7/19 15:35
 */
public abstract class TCallBack<T> extends Callback<T> {
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        T t = new Gson().fromJson(string, getClassType());
        return t;
    }
    /**
     * 获取泛型得到的.class文件
     * @return
     */
    private Type getClassType() {
        return ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
