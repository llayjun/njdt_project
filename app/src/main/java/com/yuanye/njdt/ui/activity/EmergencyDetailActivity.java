package com.yuanye.njdt.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.millet.androidlib.UI.CustomView.MyExpandableListView;
import com.millet.androidlib.UI.CustomView.TitleBar;
import com.millet.androidlib.UI.InterfaceUi.TitleBarClickListener;
import com.millet.androidlib.Utils.DateUtils;
import com.millet.androidlib.Utils.GlideUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.constants.AppUtils;
import com.yuanye.njdt.data.entity.EmergencyListEntity;
import com.yuanye.njdt.data.entity.ExpandEntity;
import com.yuanye.njdt.ui.adapter.MyExpandableAdapter;
import com.yuanye.njdt.ui.baseui.BaseModuleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by llay on 2017/9/10.
 */

public class EmergencyDetailActivity extends BaseModuleActivity implements TitleBarClickListener {

    public static void launch(Context _context, EmergencyListEntity _emergencyListEntity) {
        Intent _intent = new Intent(_context, EmergencyDetailActivity.class);
        _intent.putExtra(EMERGENCY_DETAIL_KEY, _emergencyListEntity);
        _context.startActivity(_intent);
    }

    public static final String TAG = EmergencyDetailActivity.class.getSimpleName();

    public static final String EMERGENCY_DETAIL_KEY = "EMERGENCY_DETAIL_KEY";

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.detail_time)
    TextView mDetailTime;
    @BindView(R.id.detail_describe)
    TextView mDetailDescribe;
    @BindView(R.id.detail_dispose)
    TextView mDetailDispose;
    @BindView(R.id.detail_image_show)
    ImageView mDetailImageShow;
    @BindView(R.id.detail_step_dir)
    WebView mDetailStepDirWebView;
    @BindView(R.id.emergency_expand_list_view)
    MyExpandableListView mEmergencyExpandListView;

    private MyExpandableAdapter mMyExpandableAdapter;

    //data
    private EmergencyListEntity mEmergencyListEntity;

    @Override
    protected int getViewId() {
        return R.layout.activity_emergency_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent _intent = getIntent();
        if (null != _intent) {
            mEmergencyListEntity = (EmergencyListEntity) _intent.getSerializableExtra(EMERGENCY_DETAIL_KEY);
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (null == mEmergencyListEntity) return;
        String _emergencyTitle = mEmergencyListEntity.getEmergencyTitle();
        if (!TextUtils.isEmpty(_emergencyTitle)) {
            mTitleBar.setTitleBar(_emergencyTitle, R.mipmap.nav_btn_back, 0, this);
        } else {
            mTitleBar.setTitleBar(getString(R.string.emergencyDetailFragment_title), R.mipmap.nav_btn_back, 0, this);
        }
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        long _time = mEmergencyListEntity.getCreateTime();
        if (0 != _time) {
            String _date = DateUtils.formatYMD(_time);
            if (!TextUtils.isEmpty(_date)) {
                mDetailTime.setText(_date);
            }
        }
        //紧急联系人手机号
        List<ExpandEntity> _expandEntityListGroup = new ArrayList<>();
        List<List<ExpandEntity>> _expandEntityListList = new ArrayList<>();
        String _contactPhone = mEmergencyListEntity.getContactMobile();
        String[] _phoneArray = AppUtils.getPhoneStringArray(_contactPhone);
        if (null != _phoneArray) {
            for (String _phone : _phoneArray) {
                _expandEntityListGroup.add(new ExpandEntity("紧急联系人手机", _phone));
            }
        }
        //紧急联系人名字和地址
        String _contactName = mEmergencyListEntity.getContactName();
        String[] _nameArray = AppUtils.getPhoneStringArray(_contactName);
        if (null != _nameArray) {
            for (String _nameAndPlace : _nameArray) {
                String[] _nameAndPlaceArray = AppUtils.getNameAndPlace(_nameAndPlace);
                List<ExpandEntity> _expandEntityListChild = new ArrayList<>();
                _expandEntityListChild.add(new ExpandEntity("紧急联系人", _nameAndPlaceArray[0]));
                _expandEntityListChild.add(new ExpandEntity("地点", _nameAndPlaceArray[1]));
                _expandEntityListList.add(_expandEntityListChild);
            }
        }
        mMyExpandableAdapter = new MyExpandableAdapter(this, _expandEntityListGroup, _expandEntityListList);
        mEmergencyExpandListView.setAdapter(mMyExpandableAdapter);
        //描述
        String _describe = mEmergencyListEntity.getEmergencyContent();
        if (!TextUtils.isEmpty(_describe)) {
            mDetailDescribe.setText(_describe);
        } else {
            mDetailDescribe.setText("");
        }
        String _dispose = mEmergencyListEntity.getEmergencyCircuitNumber();
        if (!TextUtils.isEmpty(_dispose)) {
            mDetailDispose.setText(_dispose);
        } else {
            mDetailDispose.setText("");
        }
        String _imageSuf = ApiConfig.IP + mEmergencyListEntity.getEmergencyPic();
        _imageSuf = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505055843956&di=ce1b26e5f0bd8ef2fdb75449aaa87f19&imgtype=0&src=http%3A%2F%2Fwww.th7.cn%2Fd%2Ffile%2Fp%2F2016%2F04%2F25%2F2686cc69720353fd7e7870bdaf91e174.jpg";
        if (!TextUtils.isEmpty(_imageSuf)) {
            GlideUtils.loadImageView(this, _imageSuf, mDetailImageShow);
        }
        String _stepStr = mEmergencyListEntity.getEventStepstr();
        if (!TextUtils.isEmpty(_stepStr)) {
            mDetailStepDirWebView.loadDataWithBaseURL("about:blank", _stepStr, "text/html", "utf-8", null);
        } else {
            mDetailStepDirWebView.setVisibility(View.GONE);
        }
    }

    @Override
    public void leftOnClick() {
        finish();
    }

    @Override
    public void rightOnClick() {

    }
}
