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

import com.longbei.lwebview.R;
import com.longbei.lwebview.base.BaseActivity;

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

//    @Override
//    protected BasePresenter createPresenter() {
//        return null;
//    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(this);
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
                    tvLogin.setEnabled(true);
                    tvLogin.setBackgroundResource(R.drawable.shape_lnp_next);
                } else {
                    tvLogin.setEnabled(false);
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
                if (!checkboxCheck.isChecked()) {
                    ToastUtils.showCenter("请先同意《中化油生活用户协议》");
                    return;
                }
                if (etName.getText().toString().isEmpty() || etPwd.getText().toString().isEmpty()) {
                    ToastUtils.showCenter("请将信息填写完整");
                    return;
                }
                //登录
                login(etName.getText().toString().trim(), etPwd.getText().toString().trim());
                break;
            case R.id.tv_registerCode:
                final String trim = etName.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtils.showCenter("请输入手机号");
                } else {
                    sendRegisterCode(trim);
                }
                break;
            case R.id.tv_agreement:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra(WebActivity.TITLE_KEY, "用户协议");
                intent.putExtra(WebActivity.URL_KEY, HttpConfig.PRIVACY_USER);
                intent.putExtra(WebActivity.SHOW_TITLE, true);
                startActivity(intent);
                break;
            case R.id.tv_privacy_policy:
                Intent i = new Intent(this, WebActivity.class);
                i.putExtra(WebActivity.TITLE_KEY, "隐私政策");
                i.putExtra(WebActivity.URL_KEY, HttpConfig.PRIVACY_POLICY);
                i.putExtra(WebActivity.SHOW_TITLE, true);
                startActivity(i);
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
                tvLogin.setEnabled(true);
                tvLogin.setBackgroundResource(R.drawable.shape_lnp_next);
            } else {
                tvLogin.setEnabled(false);
                tvLogin.setBackgroundResource(R.drawable.shape_corner_blue_unchecked_for_login);
            }

//            String trim = etName.getText().toString().trim();
//            if (!trim.equals(spManager.getUserPhone())||TextUtils.isEmpty(trim)) {
//                checkboxCheck.setChecked(false);
//            }

        }
    }

    private void login(final String username, final String securityCode) {
        Map<String, String> map = HttpPackageParams.getLoginParams(username, securityCode);
        LogUtil.d("请求参数: " + GsonUtil.gsonString(map));
        XHttp.getInstance().post(this, HttpConfig.LOGIN, map, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                LogUtil.d("用户信息------" + GsonUtil.gsonString(userInfo));
                spManager.putModel(Spkey.USERINFO, userInfo);
                spManager.setToken(userInfo.getToken());
                spManager.setLogin(true);

                spManager.saveUserPhone(etName.getText().toString().trim());
                spManager.saveUserLoginPhone(etName.getText().toString().trim());//用于登录回显

                CrashReport.setUserId(etName.getText().toString().trim());//bugly 设置userid

                PushUtils.setAlias();

                RxBus.get().send(Constants.RX_LOGIN_CODE);
                //判断是否选择账户过
                final boolean selectDefaultUser = spManager.getSelectDefaultUser(etName.getText().toString().trim());
                if (selectDefaultUser) {
                    startActivity(MainActivity.class);
                } else {
                    startActivity(SelectAccountActivity.class);
                }
                finish();
            }


            @Override
            public void onFailed(String errorCode, String error) {
                ToastUtils.showCenter(error);
                super.onFailed(errorCode, error);
            }


        });
    }


    /**
     * 发送短信验证码
     *
     * @param phone
     */
    private void sendRegisterCode(final String phone) {

        Map<String, String> map = HttpPackageParams.getSendMessageParams(phone, "");
        //18301138670   111111
//        StringUtils.isEmpty()
        LogUtil.d("请求参数: " + GsonUtil.gsonString(map));
        XHttp.getInstance().post(this, HttpConfig.SENDSMSENCPY, map, new HttpCallBack<String>() {
            @Override
            public void onSuccess(String string) {
                LogUtil.d("发送短信验证码 = " + string);
                tvRegisterCode.setClickable(false);
//                if (TextUtils.isEmpty(string)) {
//                    ToastUtils.showCenter("短信验证码发送失败");
//                } else {
                ToastUtils.showCenter("短信验证码发送成功");
                timer.start();
//                }

            }


            @Override
            public void onFailed(int errorCode, String error) {
                super.onFailed(errorCode, error);
            }

        }, true);
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
