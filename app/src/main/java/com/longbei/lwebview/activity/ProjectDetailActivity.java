package com.longbei.lwebview.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.longbei.lwebview.GlideCacheEngine;
import com.longbei.lwebview.GlideEngine;
import com.longbei.lwebview.R;
import com.longbei.lwebview.adapter.GridImageAdapter;
import com.longbei.lwebview.adapter.ProjectAdapter;
import com.longbei.lwebview.adapter.ProjectDetaiAdapter;
import com.longbei.lwebview.base.BaseActivity;
import com.longbei.lwebview.bean.TypeBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.animators.AnimationType;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureCropParameterStyle;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.style.PictureSelectorUIStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ProjectDetailActivity extends BaseActivity {

    private RecyclerView rvReportList;
    private ProjectDetaiAdapter projectDetaiAdapter;
    List<TypeBean> list = new ArrayList();
    private TypeBean typeBean;
    private PictureCropParameterStyle mCropParameterStyle;
    private final static String TAG = ProjectDetailActivity.class.getSimpleName();
    private TextView tv_commit;

    @Override
    public int setLayoutId() {
        return R.layout.activity_project;
    }

    @Override
    public void initVariables() {
        list = (List<TypeBean>) getIntent().getSerializableExtra("list");
        for (TypeBean bean : list) {
                Log.d("gson", "list: "+bean.getTitle());
            }

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        rvReportList = (RecyclerView) findViewById(R.id.rv_report_list);
        tv_commit = findViewById(R.id.tv_commit);
        tv_commit.setVisibility(View.VISIBLE);

        rvReportList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        projectDetaiAdapter = new ProjectDetaiAdapter(R.layout.activity_report_detail_list_item, list, this,false);
        rvReportList.setAdapter(projectDetaiAdapter);

        projectDetaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        projectDetaiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                typeBean = list.get(position);
                switch (view.getId()) {
                    case R.id.et_content:

                        break;
                }
            }
        });
    }

    @Override
    public void doBusiness() {
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("list", (Serializable) list);
                intent.setClass(getActivity(),PreViewReviewProjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

}
