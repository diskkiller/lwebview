package com.longbei.lwebview.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.longbei.lwebview.R;
import com.longbei.lwebview.adapter.ProjectDetaiAdapter;
import com.longbei.lwebview.base.BaseActivity;
import com.longbei.lwebview.bean.TypeBean;
import com.luck.picture.lib.style.PictureCropParameterStyle;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreViewReviewProjectDetailActivity extends BaseActivity {

    private RecyclerView rvReportList;
    private ProjectDetaiAdapter projectDetaiAdapter;
    List<TypeBean> list = new ArrayList();
    private TypeBean typeBean;
    private PictureCropParameterStyle mCropParameterStyle;
    private final static String TAG = PreViewReviewProjectDetailActivity.class.getSimpleName();

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

        rvReportList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        projectDetaiAdapter = new ProjectDetaiAdapter(R.layout.activity_report_detail_list_item, list, this,true);
        rvReportList.setAdapter(projectDetaiAdapter);


    }

    @Override
    public void doBusiness() {

    }

}
