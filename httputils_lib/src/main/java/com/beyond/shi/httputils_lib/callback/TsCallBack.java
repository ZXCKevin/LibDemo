package com.beyond.shi.httputils_lib.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;

/**得到一个集合类型的回调监听
 * @Package com.beyond.shi.httputils_lib.callback
 * @Title 
 * @Description 
 * @author WangJinya
 * @Time 2016/7/21 14:30
 */
public abstract class TsCallBack<T> extends Callback<List<T>> {
    @Override
    public List<T> parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        List<T> t = new Gson().fromJson(string, List.class);
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
