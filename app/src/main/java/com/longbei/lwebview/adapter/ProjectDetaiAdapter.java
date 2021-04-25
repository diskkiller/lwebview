package com.longbei.lwebview.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.longbei.lwebview.R;
import com.longbei.lwebview.activity.ProjectDetailActivity;
import com.longbei.lwebview.bean.TypeBean;
import com.longbei.lwebview.utils.StringUtils;

import java.util.List;

public class ProjectDetaiAdapter extends BaseQuickAdapter<TypeBean, BaseViewHolder> {
    public ProjectDetaiAdapter(int layoutResId, @Nullable List<TypeBean> data) {
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

        helper.setText(R.id.tv_report_name, item.getId()+"--"+item.getTitle());
        helper.setText(R.id.et_content, StringUtils.isEmpty(item.getContent())?"":item.getContent());
        helper.addOnClickListener(R.id.et_content);

        EditText editText = helper.getView(R.id.et_content);

        final MyTextWatcher watcher = new MyTextWatcher(item);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText mV = (EditText) v;
                if (hasFocus) {
                    mV.addTextChangedListener(watcher);
                } else {
                    mV.removeTextChangedListener(watcher);
                }
            }
        });


    }

    public class MyTextWatcher implements TextWatcher {
        TypeBean typeBean;
        public MyTextWatcher(TypeBean typeBean) {
            this.typeBean = typeBean;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(typeBean != null)
                typeBean.setContent(s.toString());
        }
    }

}
