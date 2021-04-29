package com.longbei.lwebview.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.longbei.lwebview.R;
import com.longbei.lwebview.base.BaseActivity;

import me.jessyan.autosize.AutoSizeCompat;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {


    private TextView skip;

    @Override
    public int setLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initVariables() {
        setStatusBar(R.color.app_color_transparent);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        ImageView imageView = findViewById(R.id.image);
//        GlideUtils.loadFullPhoto(this, imageView, R.mipmap.welcome, R.mipmap.no_data);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(this);

    }

    @Override
    public void doBusiness() {
        timer.start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.skip) {
            timer.onFinish();
        }
    }
    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
//        AutoSizeCompat.autoConvertDensity((super.getResources(),667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }
    private void toMainActivity() {
        /*final boolean selectDefaultUser = spManager.getSelectDefaultUser(spManager.getUserLoginPhone());

        if (selectDefaultUser|| TextUtils.isEmpty(spManager.getUserLoginPhone())) {
            startActivity(MainActivity.class);
        } else {
            startActivity(SelectAccountActivity.class);
        }*/
        startActivity(LoginActivity.class);

        finish();
    }

    CountDownTimer timer = new CountDownTimer(3 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            skip.setText("跳过" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            toMainActivity();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}