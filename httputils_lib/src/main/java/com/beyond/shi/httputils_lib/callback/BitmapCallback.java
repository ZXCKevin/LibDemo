package com.beyond.shi.httputils_lib.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * @Package com.beyond.shi.httputils_lib.callback
 * @Title 
 * @Description 
 * @author WangJinya
 * @Time 2016/7/19 16:36
 */
public abstract class BitmapCallback extends Callback<Bitmap>
{
    @Override
    public Bitmap parseNetworkResponse(Response response , int id) throws Exception
    {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
