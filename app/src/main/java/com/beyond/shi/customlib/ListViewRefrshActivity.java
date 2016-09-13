package com.beyond.shi.customlib;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.beyond.shi.customlib.view.XListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Package com.beyond.shi.customlib
 * @Title 
 * @Description 
 * @author WangJinya
 * @Time 2016/9/12 18:01
 */
public class ListViewRefrshActivity extends Activity implements XListView.IXListViewListener {
    @Bind(R.id.xlistview)
    XListView mXListView;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("item"+i);
        }
        ButterKnife.bind(this);
        mXListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,mList));
        mXListView.setPullRefreshEnable(true);
        mXListView.setPullLoadEnable(true);
        mXListView.setXListViewListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            mXListView.autoRefresh();//支持自动刷新
        }
    }
    Handler mHandler = new Handler();
    @Override
    public void onRefresh() {
        Toast.makeText(ListViewRefrshActivity.this, "刷新了", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mXListView.stopRefresh();//2秒后停止刷新
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(ListViewRefrshActivity.this, "加载了", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mXListView.stopLoadMore();//2秒后停止加载
            }
        },2000);
    }
}
