package com.longbei.lwebview.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.longbei.lwebview.R;
import com.longbei.lwebview.adapter.ReportAdapter;
import com.longbei.lwebview.bean.TypeBean;
import com.longbei.lwebview.gson.GsonUtil;
import com.longbei.lwebview.utils.C;
import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;


public class SecendFragment extends LazyFragment {




	List<TypeBean> list = new ArrayList();
	List<TypeBean> list1 = new ArrayList();
	List<TypeBean> list2 = new ArrayList();
	List<TypeBean> list3 = new ArrayList();
	private RecyclerView rvReportList;
	private ReportAdapter reportAdapter;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_secend);

        list = GsonUtil.jsonToList(C.json,TypeBean.class);
        list1 = GsonUtil.jsonToList(C.json1,TypeBean.class);
        list2 = GsonUtil.jsonToList(C.json2,TypeBean.class);
        list3 = GsonUtil.jsonToList(C.json3,TypeBean.class);
        Log.d("gson",list.toString());

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

			}
		});
	}

}
