package com.longbei.lwebview;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraXConfig;

import com.qw.soul.permission.SoulPermission;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class App extends Application implements CameraXConfig.Provider{
    public static App application;
    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        SoulPermission.init(this);

    }
    public static App getAppInstance() {
        if (application == null) {
            application = new App();
        }
        return application;
    }

    @NonNull
    @Override
    public CameraXConfig getCameraXConfig() {
        return Camera2Config.defaultConfig();
    }
}
