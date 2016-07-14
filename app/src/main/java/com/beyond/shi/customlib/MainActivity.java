package com.beyond.shi.customlib;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyond.shi.base_lib.utils.BitmapUtils;
import com.beyond.shi.base_lib.utils.HttpUtils;
import com.beyond.shi.base_lib.utils.callback.GetStringCallBack;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.iv2)
    ImageView iv2;
    @Bind(R.id.iv3)
    ImageView iv3;
    @Bind(R.id.iv4)
    ImageView iv4;
    @Bind(R.id.iv5)
    ImageView iv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initGetString();
        initImageLoad();
    }

    //加载图片
    private void initImageLoad() {
        BitmapUtils.initImage(this, iv1, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3197501779,1245095733&fm=21&gp=0.jpg"
                , R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initCircleImage(this, iv2, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3197501779,1245095733&fm=21&gp=0.jpg"
                , R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initSquareImageView(this, iv3, "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1079107641,923986729&fm=21&gp=0.jpg"
                , R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initRoundedCornerImage(this, iv4, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3466741137,3786670042&fm=21&gp=0.jpg"
                , 10, 0, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initOvalCornerImage(this,iv5,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3466741137,3786670042&fm=21&gp=0.jpg",
                4, Color.RED,20,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
    }

    //通过get请求获取字符串
    private void initGetString() {
        HttpUtils.getInstance().getStringResponse(this, "http://www.baidu.com", new GetStringCallBack() {
            @Override
            public void getStringResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                Log.i("=======",
                        "isFromCache" + isFromCache + "s: " + s + "request:" + request + "request:" + request);
                tv.setText(s);
            }

            @Override
            public void getStringFail(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtils.getInstance().cancelHttpResponse(this);//取消加载网络请求
    }
}
