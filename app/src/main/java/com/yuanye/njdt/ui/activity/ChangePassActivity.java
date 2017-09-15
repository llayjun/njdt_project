package com.yuanye.njdt.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.millet.androidlib.UI.CustomView.TitleBar;
import com.millet.androidlib.UI.InterfaceUi.TitleBarClickListener;
import com.millet.androidlib.Utils.ToastUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.presenter.engine.ChangePassEngine;
import com.yuanye.njdt.presenter.enginedelegate.ChangePassEngineDelegate;
import com.yuanye.njdt.ui.baseui.BaseModuleActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ChangePassActivity extends BaseModuleActivity implements ChangePassEngineDelegate, TitleBarClickListener {

    public static void launch(Context _context) {
        Intent _intent = new Intent(_context, ChangePassActivity.class);
        _context.startActivity(_intent);
    }

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.old_pass_word)
    EditText mOldPassWord;
    @BindView(R.id.new_pass_word)
    EditText mNewPassWord;
    @BindView(R.id.confirm_new_pass_word)
    EditText mConfirmNewPassWord;
    @BindView(R.id.button_change_word)
    Button mButtonChangeWord;

    private ChangePassEngine mChangePassEngine;

    @Override
    protected int getViewId() {
        return R.layout.activity_change_word;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mChangePassEngine = new ChangePassEngine(this, this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mTitleBar.setTitleBar(getString(R.string.app_company_regi_phone_pwd), R.mipmap.nav_btn_back, 0, this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.button_change_word)
    public void onClick() {
        String _oldPass = mOldPassWord.getText().toString().trim();
        String _newPass = mNewPassWord.getText().toString().trim();
        String _confirmPass = mConfirmNewPassWord.getText().toString().trim();
        if (TextUtils.isEmpty(_oldPass)) {
            ToastUtils.showToast(this, getString(R.string.change_pass_no_old_pass), 0);
            return;
        }
        if (TextUtils.isEmpty(_newPass)) {
            ToastUtils.showToast(this, getString(R.string.change_pass_no_new_pass), 0);
            return;
        }
        if (TextUtils.isEmpty(_confirmPass)) {
            ToastUtils.showToast(this, getString(R.string.change_pass_no_confirm_new_pass), 0);
            return;
        }
        if (!_newPass.equals(_confirmPass)) {
            ToastUtils.showToast(this, getString(R.string.change_pass_error_confirm_new_pass), 0);
            return;
        }
        if (null == mChangePassEngine) return;
        mChangePassEngine.changePassWord(_confirmPass);
    }

    @Override
    public void changePassOnSuccess(String _resultMessage) {
        ToastUtils.showToast(this, _resultMessage, 0);
        finish();
    }

    @Override
    public void changePassOnError(String _string) {
        ToastUtils.showToast(this, _string, 0);
    }

    @Override
    public void leftOnClick() {
        finish();
    }

    @Override
    public void rightOnClick() {

    }

}
