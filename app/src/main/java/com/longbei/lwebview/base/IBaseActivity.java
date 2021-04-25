package com.longbei.lwebview.base;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author liuml
 * @explain 与业务无关的 Activity 基类
 * @time 2017/12/5 09:52
 */

public abstract class IBaseActivity extends AppCompatActivity implements ICallback {


    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //这里setContentView 必须放在最前面
        setContentView(setLayoutId());
        super.onCreate(savedInstanceState);
        mContext = this;
        initVariables();
        initViews(savedInstanceState);
        doBusiness();
    }

    /**
     * 初始化Layout
     */
    public abstract int setLayoutId();

    /**
     * 初始化变量，包括 Intent 带的数据和 Activity 内的变量。
     */
    public abstract void initVariables();

   /**
     * 加载 layout 布局文件，初始化控件，为控件挂上事件方法。
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 业务操作. 比如调用 MobileAPI 获取数据。
     */
    public abstract void doBusiness();



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
