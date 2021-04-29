package com.longbei.lwebview.utils;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.android.framelib.base.IBaseFrameApplication;


/**
 * @author liuml.
 * @explain 吐司工具
 * @time 2017/12/8 17:12
 */
public class ToastUtils {


    public static void showShort(final String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            //主线程
            try {
                Toast.makeText(IBaseFrameApplication.getInstance(), str, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (AppManager.getAppManager().currentActivity() == null) {
                return;
            }
            try {
                AppManager.getAppManager().currentActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Toast.makeText(IBaseFrameApplication.getInstance(), str, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {

            }
        }

    }

    public static void showLong(String str) {
        Toast.makeText(IBaseFrameApplication.getInstance(), str, Toast.LENGTH_LONG).show();
    }

    /**
     * toast居中显示
     *
     * @param
     */
    public static void showCenter(final String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            //主线程
            try {
                Toast toast = Toast.makeText(IBaseFrameApplication.getInstance(), str, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (AppManager.getAppManager().currentActivity() == null) {
                return;
            }
            try {
                AppManager.getAppManager().currentActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Toast toast = Toast.makeText(IBaseFrameApplication.getInstance(), str, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void customToast(Context context, View view, int duration) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
