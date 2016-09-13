package com.beyond.shi.customlib;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.beyond.shi.httputils_lib.BitmapUtils;
import com.beyond.shi.httputils_lib.HttpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by guojuan on 2016/7/15.
 */
public class ImageActivity extends Activity {
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
    @Bind(R.id.iv6)
    ImageView iv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        initImageLoad();
    }

    //加载图片
    private void initImageLoad() {
        BitmapUtils.initImage(this, iv1, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3197501779,1245095733&fm=21&gp=0.jpg"
                , R.drawable.ic_launcher, R.drawable.ic_launcher);
        BitmapUtils.initCircleImage(this, iv2, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3197501779,1245095733&fm=21&gp=0.jpg"
                , R.drawable.ic_launcher, R.drawable.ic_launcher);
        BitmapUtils.initSquareImageView(this, iv3, "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1079107641,923986729&fm=21&gp=0.jpg"
                , R.drawable.ic_launcher, R.drawable.ic_launcher);
        BitmapUtils.initRoundedCornerImage(this, iv4, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3466741137,3786670042&fm=21&gp=0.jpg"
                , 10, 0, R.drawable.ic_launcher, R.drawable.ic_launcher);
        BitmapUtils.initOvalCornerImage(this, iv5, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3466741137,3786670042&fm=21&gp=0.jpg",
                4, Color.RED, 20, R.drawable.ic_launcher, R.drawable.ic_launcher);
        BitmapUtils.initCircleImage(this,iv6,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3466741137,3786670042&fm=21&gp=0.jpg"
        ,R.drawable.ic_launcher,R.drawable.ic_launcher,5,Color.YELLOW);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtils.getInstance().cancelResponse(this);
    }
}
