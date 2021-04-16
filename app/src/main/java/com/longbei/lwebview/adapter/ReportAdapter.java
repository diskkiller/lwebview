package com.longbei.lwebview.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.longbei.lwebview.bean.TypeBean;

import java.util.List;

public class ReportAdapter extends BaseQuickAdapter<TypeBean, BaseViewHolder> {
    public ReportAdapter(int layoutResId, @Nullable List<TypeBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, TypeBean item) {
			/*String runNum = "进行中 " + item.getCollectingNum();
			helper.setText(R.id.tv_report_name, item.getName())
					.setText(R.id.field_control_num, item.getNum() + "篇报告");
			if (item.getCollectingNum() <= 0) {
				helper.setVisible(R.id.field_control_running_num, false);
			} else {
				helper.setVisible(R.id.field_control_running_num, true)
						.setText(R.id.field_control_running_num, runNum);
			}
			ImageView view = helper.getView(R.id.iv_report_item_icon);
			GlideUtils.loadRoundedCorner(getApplicationContext(), view, item.getIcon());*/


    }
}
