package com.beyond.shi.customlib;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beyond.shi.customlib.data.Bean;
import com.beyond.shi.httputils_lib.HttpUtils;
import com.beyond.shi.httputils_lib.callback.StringCallback;
import com.beyond.shi.httputils_lib.callback.TCallBack;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by wangjinya on 2016/7/15.
 */
public class HttpActivity extends Activity {
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.btn_up)
    Button btnUp;
    @Bind(R.id.pb)
    ProgressBar pb;
    @Bind(R.id.tv2)
    TextView mTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);
        initHttps();
    }

    private void initHttps() {
        //请求示例
        HttpUtils.getInstance().getStringResponse(this, "http://www.baidu.com", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                tv.setText(e.toString());//错误信息
            }

            @Override
            public void onResponse(String response, int id) {
                tv.setText(response);
            }
        });
    HttpUtils.getInstance().getStringResponse(this, "http://cn.bing.com/cnhp/coverstory/", new TCallBack<Bean>() {
        @Override
        public void onError(Call call, Exception e, int id) {
            mTv2.setText(e.toString());//错误信息
        }

        @Override
        public void onResponse(Bean response, int id) {
            mTv2.setText("实体类示例Bean：："+response.toString());
        }


    });
        //下载示例：
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtils.getInstance().downFileResponse(HttpActivity.this, "http://gdown.baidu.com/data/wisegame/b84c9708a92327ed/jinritoutiao_570.apk", Environment.getExternalStorageDirectory().getAbsolutePath(), "jiritoutiao.apk"
                        , new DownFileCallBack() {
                            @Override
                            public void inProgress(float progress, long total, int id) {
                                btnUp.setClickable(false);//设置不可点击
                                btnUp.setText("已下载" + (int) (progress * 100) + "%");
                                pb.setProgress((int) (progress * 100));
                            }

                            @Override
                            public void downFileErro(Call call, Exception e, int id) {
                                Toast.makeText(HttpActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                                btnUp.setClickable(true);//可点击
                                btnUp.setText("重新下载");
                            }

                            @Override
                            public void downFileSuccess(File response, int id) {
                                Toast.makeText(HttpActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                                btnUp.setText("下载完成");
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtils.getInstance().cancelResponse(this);
    }
}
