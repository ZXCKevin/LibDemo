package com.beyond.shi.customlib.Fragment;

import android.support.v4.app.Fragment;

/**
 * 描述：Fragment基础类
 * 作者：Administrator on 2016/9/5 11:32
 */
public class BaseFragment extends Fragment {
    
    protected IFragmentData iFragmentData;
    
    /**
     *描述: 用于Fragment与其寄宿的Activity之间的数据传递
     *作者: Administrator on 2016/9/5 16:44
     */
    public interface IFragmentData{
        public <T> void getIData(T data); 
    }
    
    /**
     *描述: 设置数据传递接口
     *作者: Administrator on 2016/9/5 17:22
     */
    public void setIFragmentData(IFragmentData iFragmentData){
        this.iFragmentData = iFragmentData;
    }
}
