package com.longbei.lwebview;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.longbei.lwebview.dialog.PictureCustomDialog;
import com.longbei.lwebview.fragments.SecendFragment;
import com.longbei.lwebview.fragments.ThirdFragment;
import com.longbei.lwebview.fragments.WebFragment;
import com.longbei.lwebview.utils.Utils;
import com.longbei.lwebview.wegit.BottomBar;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;

public class MainActivity extends AppCompatActivity {
    private String title[] = {"首页", "消息", "我的"};
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermission();
        initViewpager();
    }


    private void checkAndRequestPermission() {
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build( Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE),
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] permissions) {
//                        Toast.makeText(LoginActivity.this, permissions.toString() +
//                                "已授权", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(Permission[] permissions) {

                        for (Permission permission : permissions) {
                            if (permission.shouldRationale()) {
//                                Toast.makeText(LoginActivity.this, permission.toString() +
//                                        " 点击拒绝 ", Toast.LENGTH_SHORT).show();

                            } else {
//                                Toast.makeText(LoginActivity.this, permission.toString() +
//                                        " 点击拒绝并勾选不再提示", Toast.LENGTH_SHORT).show();
                            }
                        }

                        show_tips();

                    }

                });
    }

    private void initViewpager() {
        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.scroll_container)
                .setTitleBeforeAndAfterColor("#999999", "#62B66C")
                .addItem(WebFragment.class,
                        "首页",
                        R.mipmap.ic_bottom_home_icon,
                        R.mipmap.ic_bottom_home_icon)
                .addItem(SecendFragment.class,
                        "XXX",
                        R.mipmap.ic_bottom_mv_unselect,
                        R.mipmap.ic_bottom_mv_unselect)
                .addItem(ThirdFragment.class,
                        "XXX",
                        R.mipmap.ic_bottom_mvlist_unselect,
                        R.mipmap.ic_bottom_mvlist_unselect)
                .addItem(ThirdFragment.class,
                        "XXX",
                        R.mipmap.ic_bottom_mvlist_unselect,
                        R.mipmap.ic_bottom_mvlist_unselect)
                .build();
        bottomBar.setOnSwitcnListener(new BottomBar.SwitcnListener() {
            @Override
            public void onSwitcn(int currentCheckedIndex) {
//                mToolbar.setTitle(title[currentCheckedIndex]);

            }
        });

    }

    private void show_tips(){

        final PictureCustomDialog dialog =
                new PictureCustomDialog(this, R.layout.picture_wind_base_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        Button btn_commit = dialog.findViewById(R.id.btn_commit);
        btn_commit.setText(getString(R.string.picture_go_setting));
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tv_content = dialog.findViewById(R.id.tv_content);
        tvTitle.setText(getString(R.string.picture_prompt));
        tv_content.setText(R.string.string_help_text);
        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btn_commit.setOnClickListener(v -> {
            goApplicationSettings();
            dialog.dismiss();
        });
        dialog.show();

    }

    public void goApplicationSettings(){
        SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() {
            @Override
            public void onBackFromAppDetail(Intent data) {
                checkAndRequestPermission();
            }
        });
    }


    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    private static final int TIME_EXIT = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_EXIT > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Utils.showToastCenter(this, "再点击一次退出程序");
            mBackPressed = System.currentTimeMillis();

        }
    }

}
