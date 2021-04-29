package com.longbei.lwebview.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.longbei.lwebview.R;
import com.longbei.lwebview.utils.StatusBarUtil;

import java.util.List;

/**
 * @author liuml
 * @explain
 * @time 2018/10/15 11:12
 */
public abstract class BaseActivity extends IBaseActivity {
    private RelativeLayout baseRelativeLayout;
    private RelativeLayout rlTitle;
    private LinearLayout llLeft;
    private TextView tvLeft;
    private ImageView ivLeft;
    private TextView titleName;
    public LinearLayout llRight;
    private TextView tvLeftright;
    private TextView tvRight;
    private RelativeLayout rlSearch;
    private TextView txtCancel;
    protected Activity mAct;
    private static final String TAG = "BaseActivity";
    private ImageView ivMore;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAct = this;
    }

    public void initBaseTitle() {

        setStatusBar();

        baseRelativeLayout = findViewById(R.id.base_relative_layout);
        rlTitle = findViewById(R.id.rl_title);
        llLeft = findViewById(R.id.ll_left);
        tvLeft = findViewById(R.id.tv_left);
        ivLeft = findViewById(R.id.iv_left);
        titleName = findViewById(R.id.title_name);
        llRight = findViewById(R.id.ll_right);
        tvLeftright = findViewById(R.id.tv_leftright);
        tvRight = findViewById(R.id.tv_right);
//        rlSearch = findViewById(R.id.rl_search);
        ivMore = (ImageView) findViewById(R.id.iv_more);

        if (llLeft != null) {
            llLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


    }

    public void setIvMore() {
        ivMore.setVisibility(View.VISIBLE);
    }


    public Activity getActivity() {
        return mAct;
    }


    protected void setStatusBar() {
        int color = getResources().getColor(R.color.app_color_white);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
    }

    protected void setStatusGaryBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
    }


    protected void setStatusBar(int color) {
        StatusBarUtil.setColor(this, getResources().getColor(color), 112);
    }


    public void setStatusBarWhite() {
        int color = getResources().getColor(R.color.app_color_white);
        StatusBarUtil.setColor(this, color,0);
        StatusBarUtil.setLightMode(this);
    }

   /* public void setStatusBarBlue() {
        int color = getResources().getColor(R.color.color_0063F5);
        StatusBarUtil.setColor(this, color,0);
    }

    public void setTiTleBlue() {
        int color = getResources().getColor(R.color.color_0063F5);
        rlTitle.setBackgroundColor(color);
    }
*/
    public void setTitle(String title) {
        initBaseTitle();
        if (titleName != null) {
            titleName.setText(title);
        }
    }


    /**
     * 设置状态栏颜色 * * @param activity 需要设置的activity * @param color 状态栏颜色值
     */
    @SuppressLint("NewApi")
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color 状态栏颜色值 * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    /**
     * 使状态栏透明 * <p> * 适用于图片作为背景的界面,此时需要图片填充到状态栏 * * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
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
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("BaseActivity  onActivityResult");
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
            Fragment fragment = fragmentManager.getFragments().get(i);
            if (fragment == null) {
//                FontLog.i(getClass(), Integer.toHexString(requestCode));
            } else {
                handleResult(fragment, requestCode, resultCode, data);
            }
        }
    }

    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
        if (childFragment != null)
            for (Fragment f : childFragment)
                if (f != null) {
                    handleResult(f, requestCode, resultCode, data);
                }
        if (childFragment == null) {
//            FontLog.i(getClass(), "MyBaseFragmentActivity is null");
        }
    }

}
