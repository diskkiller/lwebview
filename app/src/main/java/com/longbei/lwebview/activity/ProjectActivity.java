package com.longbei.lwebview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.longbei.lwebview.R;
import com.longbei.lwebview.adapter.ProjectAdapter;
import com.longbei.lwebview.adapter.ReportAdapter;
import com.longbei.lwebview.base.BaseActivity;
import com.longbei.lwebview.bean.TypeBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends BaseActivity {

    private RecyclerView rvReportList;
    private ProjectAdapter projectAdapter;
    List<TypeBean> list = new ArrayList();
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
        initBaseTitle();

        rvReportList = (RecyclerView) findViewById(R.id.rv_report_list);

        rvReportList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        projectAdapter = new ProjectAdapter(R.layout.fragment_report_list_itemm, list);
        rvReportList.setAdapter(projectAdapter);

        projectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("list", (Serializable) list.get(position).getList());
                intent.setClass(getActivity(),ProjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void doBusiness() {

    }
}
