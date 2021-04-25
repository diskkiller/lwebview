package com.longbei.lwebview.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.longbei.lwebview.R;
import com.longbei.lwebview.activity.ProjectActivity;
import com.longbei.lwebview.adapter.ReportAdapter;
import com.longbei.lwebview.bean.TypeBean;
import com.longbei.lwebview.gson.GsonUtil;
import com.longbei.lwebview.utils.C;
import com.shizhefei.fragment.LazyFragment;

import java.io.Serializable;
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
				intent.setClass(getActivity(),ProjectActivity.class);
				startActivity(intent);
			}
		});
	}

}
