package com.beyond.shi.customlib.Activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyond.shi.customlib.R;

/**
 *描述: 加载页面，同时兼顾处理加载、空数据页面、网络错误页面
 *作者: Administrator on 2016/9/6 10:48
 */
public class BaseLoadActivity extends BaseActivity{
    protected View mLoadingView;
    protected View mEmptyView;
    protected View mErrorView;
    protected FrameLayout mContentView;
    private com.ldoublem.loadingviewlib.LVCircularJump mLoadControl;
    private TextView mLoadDescripe;
    private ImageView mEmptyControl;
    private TextView mEmptyDescripe;
    private ImageView mErrorControl;
    private TextView mErrorDescripe;
    private Button mErrorAction;

    public enum StateView{LOAD,EMPTY,ERROR,CONTENT}

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

        mLoadControl = bind(mLoadingView,R.id.base_loading);
        mLoadDescripe = bind(mLoadingView,R.id.tv_base_loading_descripe);
        mEmptyControl = bind(mEmptyView,R.id.iv_base_empty);
        mEmptyDescripe = bind(mEmptyView,R.id.tv_base_empty_descripe);
        mErrorControl = bind(mErrorView,R.id.iv_base_error);
        mErrorDescripe = bind(mErrorView,R.id.tv_base_error_descirpe);
        mErrorAction = bind(mErrorView,R.id.btn_base_error_action);

        showStateLayout(StateView.CONTENT);
        setEmptyViewDTO(0,0);//此处设置默认的图片和文字ID
        setErrorViewDTO(0,0,0);
    }

    @Override
    public void setListener() {
        mEmptyView.setOnClickListener(this);
        mErrorView.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.layout_empty:
                onEmptyViewClick();
                break;
            case R.id.layout_error:
                onErrorViewClick();
                break;
        }
    }

    /**
     *描述: 设置要显示的View，根据枚举设置
     *作者: Administrator on 2016/9/8 16:19
     */
    protected void showStateLayout(StateView stateView){
        switch (stateView){
            case LOAD:
                mContentView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadControl.startAnim();
                break;
            case EMPTY:
                mContentView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                mErrorView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadControl.stopAnim();
                break;
            case ERROR:
                mContentView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.VISIBLE);
                mLoadingView.setVisibility(View.GONE);
                mLoadControl.stopAnim();
                break;
            case CONTENT:
                mContentView.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadControl.stopAnim();
                break;
            default:
                mContentView.setVisibility(View.VISIBLE);
                mEmptyView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mLoadControl.stopAnim();
                break;
        }
    }

    /**
     *描述: 设置空数据页面资源
     *作者: Administrator on 2016/9/8 16:22
     */
    protected void setEmptyViewDTO(int imgId,int strId){
        //TODO 此处可以设置默认的图片和文字资源，子类重写此方法可设置新的资源
        mEmptyControl.setImageResource(imgId);
        mEmptyDescripe.setText(getResources().getString(strId));
    }

    /**
     *描述: 设置错误页面资源
     *作者: Administrator on 2016/9/8 16:31
     */
    protected void setErrorViewDTO(int imgId,int strId,int btnStr){
        mErrorControl.setImageResource(imgId);
        mErrorDescripe.setText(getResources().getString(strId));
        mErrorAction.setText(getResources().getString(btnStr));
    }

    /**
     *描述: 空数据页面点击事件处理
     *作者: Administrator on 2016/9/8 16:01
     */
    protected void onEmptyViewClick(){}

    /**
     *描述: 错误数据页面点击事件处理
     *作者: Administrator on 2016/9/8 16:02
     */
    protected void onErrorViewClick(){}
}
