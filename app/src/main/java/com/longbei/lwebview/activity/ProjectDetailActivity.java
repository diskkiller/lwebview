package com.longbei.lwebview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.longbei.lwebview.R;
import com.longbei.lwebview.adapter.ProjectAdapter;
import com.longbei.lwebview.adapter.ProjectDetaiAdapter;
import com.longbei.lwebview.base.BaseActivity;
import com.longbei.lwebview.bean.TypeBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectDetailActivity extends BaseActivity {

    private RecyclerView rvReportList;
    private ProjectDetaiAdapter projectDetaiAdapter;
    List<TypeBean> list = new ArrayList();
    private TypeBean typeBean;

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

        projectDetaiAdapter = new ProjectDetaiAdapter(R.layout.activity_report_detail_list_item, list);
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

    }
}
