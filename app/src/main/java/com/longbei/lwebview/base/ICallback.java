package com.longbei.lwebview.base;

import android.os.Bundle;


interface ICallback {
   /**
    * 初始化Layout
    */
     int setLayoutId();

   /**
    * 初始化变量，包括 Intent 带的数据和 Activity 内的变量。
    */
     void initVariables();

   /**
    * 加载 layout 布局文件，初始化控件，为控件挂上事件方法。
    *
    * @param savedInstanceState
    */
     void initViews(Bundle savedInstanceState);

   /**
    * 业务操作. 比如调用 MobileAPI 获取数据。
    */
     void doBusiness();
}
