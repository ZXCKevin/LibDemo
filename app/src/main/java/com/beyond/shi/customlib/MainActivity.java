package com.beyond.shi.customlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyond.shi.base_lib.utils.BitmapUtils;
import com.beyond.shi.base_lib.utils.HttpUtils;
import com.beyond.shi.base_lib.utils.callback.GetStringCallBack;
import com.beyond.shi.base_lib.utils.callback.LoadImageCallBack;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.iv1)
    ImageView iv1;
    @InjectView(R.id.iv2)
    ImageView iv2;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.iv3)
    ImageView iv3;
    @InjectView(R.id.iv4)
    ImageView iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initGetString();
        initImageLoad();
    }

    //加载图片
    private void initImageLoad() {
        BitmapUtils.initImage(this, iv1, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3197501779,1245095733&fm=21&gp=0.jpg"
                , R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initImage(this, iv2, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3197501779,1245095733&fm=21&gp=0.jpg"
                , BitmapUtils.CIRCLE_IMAGE_MODE, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initImage(this, iv3, "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1079107641,923986729&fm=21&gp=0.jpg"
                , BitmapUtils.GPUFILTER_IMAGE_MODE, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        BitmapUtils.initRoundedCornerImage(this, iv4, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3466741137,3786670042&fm=21&gp=0.jpg"
                , 10, 2, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    //通过get请求获取字符串
    private void initGetString() {
        HttpUtils.getInstance().getStringResponse(this, "http://www.baidu.com", new GetStringCallBack() {
            @Override
            public void getStringResponse(String response) {
                tv.setText(response);
            }

            @Override
            public void getStringFail(Call call, Exception e, int id) {
                Log.i("==========", "===" + id + e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtils.getInstance().cancelHttpResponse(this);//取消加载网络请求
    }
}
