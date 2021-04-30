package com.longbei.lwebview;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.longbei.lwebview.activity.ProjectActivity;
import com.longbei.lwebview.adapter.ReportAdapter;
import com.longbei.lwebview.bean.TypeBean;
import com.longbei.lwebview.dialog.PictureCustomDialog;
import com.longbei.lwebview.fragments.SecendFragment;
import com.longbei.lwebview.fragments.ThirdFragment;
import com.longbei.lwebview.fragments.WebFragment;
import com.longbei.lwebview.gson.GsonUtil;
import com.longbei.lwebview.utils.C;
import com.longbei.lwebview.utils.Utils;
import com.longbei.lwebview.wegit.BottomBar;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivityTest extends AppCompatActivity {

    List<TypeBean> list = new ArrayList();
    List<TypeBean> list1 = new ArrayList();
    List<TypeBean> list2 = new ArrayList();
    List<TypeBean> list3 = new ArrayList();
    private RecyclerView rvReportList;
    private ReportAdapter reportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_secend);
        checkAndRequestPermission();
        list = GsonUtil.jsonToList(C.json,TypeBean.class);
        list1 = GsonUtil.jsonToList(C.json1,TypeBean.class);
        list2 = GsonUtil.jsonToList(C.json2,TypeBean.class);
        list3 = GsonUtil.jsonToList(C.json3,TypeBean.class);

        for (TypeBean typeBean : list) {
            for (TypeBean bean : list1) {
                if(bean.getParentId().equals(typeBean.getId())){
                    typeBean.getList().add(bean);
                    Log.d("gson", "list: "+bean.getParentId());
                }
            }
        }

        for (TypeBean typeBean : list1) {
            for (TypeBean bean : list2) {
                if(bean.getParentId().equals(typeBean.getId())){
                    typeBean.getList().add(bean);
                    Log.d("gson", "list1: "+bean.getParentId());
                }
            }
        }

        for (TypeBean typeBean : list2) {
            for (TypeBean bean : list3) {
                if(bean.getParentId().equals(typeBean.getId())){
                    typeBean.getList().add(bean);
                    Log.d("gson", "list2: "+bean.getParentId());
                }
            }
        }


        initView();
    }
    private void initView() {
        rvReportList = (RecyclerView) findViewById(R.id.rv_report_list);

        rvReportList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        reportAdapter = new ReportAdapter(R.layout.fragment_report_list_itemm, list);
        rvReportList.setAdapter(reportAdapter);

        reportAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("list", (Serializable) list.get(position).getList());
                intent.setClass(MainActivityTest.this, ProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkAndRequestPermission() {
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build( Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
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
        //System.exit(0);
    }

    private static final int TIME_EXIT = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_EXIT > System.currentTimeMillis()) {
           finish();
        } else {
            Utils.showToastCenter(this, "再点击一次退出程序");
            mBackPressed = System.currentTimeMillis();

        }
    }

}
