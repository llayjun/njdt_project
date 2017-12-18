package com.yuanye.njdt.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.millet.androidlib.UI.CustomView.LineEditText;
import com.millet.androidlib.Utils.SharedPreferencesHelper;
import com.millet.androidlib.Utils.ToastUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.data.entity.UserInfoEntity;
import com.yuanye.njdt.presenter.engine.LoginEngine;
import com.yuanye.njdt.presenter.enginedelegate.LoginEngineDelegate;
import com.yuanye.njdt.ui.baseui.BaseModuleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class LoginActivity extends BaseModuleActivity implements LoginEngineDelegate {

    public static final String IS_SAVE_PASS = "IS_SAVE_PASS";
    public static final String SAVE_NAME = "SAVE_NAME";
    public static final String SAVE_PASS = "SAVE_PASS";

    @BindView(R.id.button_login)
    Button mButtonLogin;
    @BindView(R.id.user_name)
    LineEditText mUserName;
    @BindView(R.id.user_password)
    LineEditText mUserPassword;
    @BindView(R.id.save_pass_check)
    CheckBox mCheckBox;

    private String mUserNameText;
    private String mPassWordText;
    private LoginEngine mLoginEngine;

    @Override
    protected void initData(Bundle savedInstanceState) {
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mLoginEngine = new LoginEngine(this, this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        boolean _isSavePass = (Boolean) SharedPreferencesHelper.getInstance(this).get(IS_SAVE_PASS, false);
        if (_isSavePass) {
            String _userName = (String) SharedPreferencesHelper.getInstance(this).get(SAVE_NAME, "");
            String _passWord = (String) SharedPreferencesHelper.getInstance(this).get(SAVE_PASS, "");
            if (!TextUtils.isEmpty(_userName))
                mUserName.setText(_userName);
            if (!TextUtils.isEmpty(_passWord))
                mUserPassword.setText(_passWord);
        }
    }

    @OnClick(R.id.button_login)
    public void onClick() {
        hideSoftKeyboard();
        String _userName = mUserName.getText().toString().trim();
        String _passWord = mUserPassword.getText().toString().trim();
        if (TextUtils.isEmpty(_userName)) {
            ToastUtils.showToast(this, getString(R.string.login_no_user_name), 0);
            return;
        }
        if (TextUtils.isEmpty(_passWord)) {
            ToastUtils.showToast(this, getString(R.string.login_no_pass_word), 0);
            return;
        }
        if (null == mLoginEngine) return;
        mUserNameText = _userName;
        mPassWordText = _passWord;
        mLoginEngine.userLogin(_userName, _passWord);
    }

    @Override
    public void loginOnSuccess(UserInfoEntity _userInfoEntity) {
        if (mCheckBox.isChecked()) {
            if (!TextUtils.isEmpty(mUserNameText)) {
                SharedPreferencesHelper.getInstance(LoginActivity.this).put(SAVE_NAME, mUserNameText);
            }
            if (!TextUtils.isEmpty(mPassWordText)) {
                SharedPreferencesHelper.getInstance(LoginActivity.this).put(SAVE_PASS, mPassWordText);
            }
            if (!TextUtils.isEmpty(mUserNameText) && !TextUtils.isEmpty(mPassWordText)) {
                SharedPreferencesHelper.getInstance(LoginActivity.this).put(IS_SAVE_PASS, true);
            }
        } else {
            SharedPreferencesHelper.getInstance(LoginActivity.this).put(IS_SAVE_PASS, false);
        }
        MainActivity.launch(this);
        finish();
    }

    @Override
    public void loginOnError(String _string) {
        ToastUtils.showToast(this, _string, 0);
    }

}
