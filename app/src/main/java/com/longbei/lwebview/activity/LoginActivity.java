package com.longbei.lwebview.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longbei.lwebview.MainActivity;
import com.longbei.lwebview.MainActivityTest;
import com.longbei.lwebview.R;
import com.longbei.lwebview.base.BaseActivity;
import com.longbei.lwebview.gson.GsonUtil;
import com.longbei.lwebview.utils.Utils;
import com.luck.picture.lib.tools.ToastUtils;

import java.util.Map;



public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etPwd;
    private TextView tvRegisterCode;
    private TextView tvLogin;
    private LinearLayout llCheck;
    private CheckBox checkboxCheck;
    private TextView tvCheck;
    private TextView tvAgreement, tv_privacy_policy;

    @Override
    public int setLayoutId() {
        return R.layout.login_view;
    }

    @Override
    public void initVariables() {
        setTitle("");
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        tvRegisterCode = findViewById(R.id.tv_registerCode);
        tvLogin = findViewById(R.id.tv_login);
        llCheck = findViewById(R.id.ll_check);
        checkboxCheck = findViewById(R.id.checkbox_check);
        tvCheck = findViewById(R.id.tv_check);
        tvAgreement = findViewById(R.id.tv_agreement);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);

        tvRegisterCode.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        llCheck.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);

        etName.addTextChangedListener(new EditChangedListener());
        etPwd.addTextChangedListener(new EditChangedListener());
        checkboxCheck.setChecked(false);
        checkboxCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!etName.getText().toString().isEmpty() && !etPwd.getText().toString().isEmpty() && checkboxCheck.isChecked()) {
                    //tvLogin.setEnabled(true);
                    tvLogin.setBackgroundResource(R.drawable.shape_lnp_next);
                } else {
                    //tvLogin.setEnabled(false);
                    tvLogin.setBackgroundResource(R.drawable.shape_corner_blue_unchecked_for_login);
                }
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                if (etName.getText().toString().isEmpty() || etPwd.getText().toString().isEmpty()) {
                    Utils.showToastCenter(mContext,"请填写用户名或密码");
                    return;
                }
                if (!checkboxCheck.isChecked()) {
                    Utils.showToastCenter(mContext,"请先同意《用户协议》");
                    return;
                }
                //登录
                login(etName.getText().toString().trim(), etPwd.getText().toString().trim());
                break;
            case R.id.tv_registerCode:
                final String trim = etName.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Utils.showToastCenter(mContext,"请输入手机号");
                } else {
                    sendRegisterCode(trim);
                }
                break;
            case R.id.tv_agreement:

                break;
            case R.id.tv_privacy_policy:

                break;
            case R.id.tv_check:
            case R.id.ll_check:
                if (checkboxCheck.isChecked()) {
                    checkboxCheck.setChecked(false);
                } else {
                    checkboxCheck.setChecked(true);
                }
                break;
        }
    }


    class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            errorHint.setVisibility(View.INVISIBLE);
            String mStrName = etName.getText().toString().trim();
            String mStrPwd = etPwd.getText().toString().trim();
            if (!mStrName.isEmpty() && !mStrPwd.isEmpty() && checkboxCheck.isChecked()) {
                //tvLogin.setEnabled(true);
                tvLogin.setBackgroundResource(R.drawable.shape_lnp_next);
            } else {
                //tvLogin.setEnabled(false);
                tvLogin.setBackgroundResource(R.drawable.shape_corner_blue_unchecked_for_login);
            }

        }
    }

    private void login(final String username, final String securityCode) {
        startActivity(MainActivityTest.class);
        finish();
    }


    /**
     * 发送短信验证码
     *
     * @param phone
     */
    private void sendRegisterCode(final String phone) {
        Utils.showToastCenter(mContext,"短信验证码发送成功");
        timer.start();
    }


    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvRegisterCode.setText("还剩" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvRegisterCode.setText("重新发送");
            tvRegisterCode.setClickable(true);
        }
    };

}
