package com.beyond.shi.customlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_http)
    Button btnHttp;
    @Bind(R.id.btn_image)
    Button btnImage;
    @Bind(R.id.btn_list_refresh)
    Button btn_list_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        initGetString();
//        initImageLoad();
    }

    @OnClick({R.id.btn_http, R.id.btn_image,R.id.btn_list_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_http:
                startActivity(new Intent(this,HttpActivity.class));
                break;
            case R.id.btn_image:
                startActivity(new Intent(this,ImageActivity.class));
                break;
            case R.id.btn_list_refresh:
                startActivity(new Intent(this,ListViewRefrshActivity.class));
                break;
        }
    }
}
