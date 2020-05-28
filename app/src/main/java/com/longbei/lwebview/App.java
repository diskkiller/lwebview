package com.longbei.lwebview;


import android.app.Application;

import com.qw.soul.permission.SoulPermission;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        SoulPermission.init(this);

    }

}
