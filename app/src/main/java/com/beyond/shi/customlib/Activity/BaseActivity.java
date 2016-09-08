package com.beyond.shi.customlib.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/7/21.
 */
public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener{

    /** 是否沉浸状态栏 **/
    private boolean isSetStatusBar = true;
    /** 是否允许全屏 **/
    private boolean mAllowFullScreen = true;
    /** 是否禁止旋转屏幕 **/
    private boolean isAllowScreenRoate = false;
    /** 当前Activity渲染的视图View **/
    private View mContextView = null;
    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    protected EventBus mEventBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this)
                    .inflate(bindLayout(), null);
        } else
            mContextView = mView;
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView(mContextView);
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mEventBus = EventBus.getDefault();
        EventBus.getDefault().register(this);//注册EventBus
        initView(mContextView);
        setListener();
        doBusiness(this);
    }

    /**
     *描述: 沉沁状态栏
     *作者: Administrator on 2016/9/8 15:42
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     *描述: 初始化其它页面传过来的参数（若使用EventBus传递，此方法可不用）
     *作者: Administrator on 2016/9/8 15:41
     */
    public  void initParms(Bundle parms){};

    /**
     *描述: 绑定视图布局
     *作者: Administrator on 2016/9/8 15:40
     */
    public  View bindView(){return null;}

    /**
     *描述: 绑定布局
     *作者: Administrator on 2016/9/8 15:40
     */
    public abstract int bindLayout();

    /**
     *描述: 初始化控件
     *作者: Administrator on 2016/9/8 15:39
     */
    public abstract void initView(final View view);

    /** View点击 **/
    public  void widgetClick(View v){};

    /**
     *描述: 绑定控件
     *作者: Administrator on 2016/9/8 15:39
     */
    protected    <T extends View> T bind(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     *描述: 绑定View内部控件
     *作者: Administrator on 2016/9/8 15:37
     */
    protected <T extends View> T bind(View v,int resId){
        return (T) super.findViewById(resId);
    }

    /**
     *描述: 设置监听
     *作者: Administrator on 2016/9/8 15:39
     */
    public void setListener(){};

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     *描述: 业务处理
     *作者: Administrator on 2016/9/8 15:43
     */
    public  void doBusiness(Context mContext){};

    /**
     *描述: 页面跳转
     *作者: Administrator on 2016/9/8 15:43
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this,clz));
    }

    /**
     *描述: 携带数据跳转页面
     *作者: Administrator on 2016/9/8 15:43
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /*
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.d(TAG, "onDestroy()");
    }

    /**
     * 简化Toast
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 是否允许全屏
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * 是否设置沉浸状态栏
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 是否允许屏幕旋转
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }
}
