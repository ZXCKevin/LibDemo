package com.beyond.shi.customlib.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.beyond.shi.customlib.R;

/**
 *描述: 加载页面，同时兼顾处理加载、空数据页面、网络错误页面
 *作者: Administrator on 2016/9/6 10:48
 */
public class BaseLoadActivity extends BaseActivity{
    protected View mLoadingView;
    protected View mEmptyView;
    protected View mErrorView;
    protected LinearLayout mContentView;
    @Override
    public void widgetClick(View v) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_load;
    }

    @Override
    public void initView(View view) {
        mContentView = bind(R.id.content);
        mEmptyView = bind(R.id.layout_empty);
        mErrorView = bind(R.id.layout_error);
        mLoadingView = bind(R.id.layout_load);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
