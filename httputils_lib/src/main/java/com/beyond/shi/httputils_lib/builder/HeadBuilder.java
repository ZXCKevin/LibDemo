package com.beyond.shi.httputils_lib.builder;
import com.beyond.shi.httputils_lib.OkHttpUtils;
import com.beyond.shi.httputils_lib.request.OtherRequest;
import com.beyond.shi.httputils_lib.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
