package com.yuanye.njdt.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.millet.androidlib.UI.CustomView.MyExpandableListView;
import com.millet.androidlib.UI.CustomView.TitleBar;
import com.millet.androidlib.UI.InterfaceUi.TitleBarClickListener;
import com.millet.androidlib.Utils.DateUtils;
import com.millet.androidlib.Utils.ToastUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.constants.AppUtils;
import com.yuanye.njdt.data.entity.EmergencyListEntity;
import com.yuanye.njdt.data.entity.ExpandEntity;
import com.yuanye.njdt.data.entity.MaterialEntity;
import com.yuanye.njdt.presenter.engine.HistoryDetailEngine;
import com.yuanye.njdt.presenter.enginedelegate.HistoryDetailEngineDelegate;
import com.yuanye.njdt.ui.adapter.MyExpandableAdapter;
import com.yuanye.njdt.ui.adapter.MyMaterialExpandableAdapter;
import com.yuanye.njdt.ui.baseui.BaseModuleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by llay on 2017/9/10.
 */

public class HistoryDetailActivity extends BaseModuleActivity implements TitleBarClickListener, HistoryDetailEngineDelegate, ExpandableListView.OnGroupClickListener {

    public static void launch(Context _context, EmergencyListEntity _emergencyListEntity) {
        Intent _intent = new Intent(_context, HistoryDetailActivity.class);
        _intent.putExtra(HISTORY_DETAIL_KEY, _emergencyListEntity);
        _context.startActivity(_intent);
    }

    public static final String TAG = HistoryDetailActivity.class.getSimpleName();

    public static final String HISTORY_DETAIL_KEY = "HISTORY_DETAIL_KEY";

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.history_detail_create_time)
    TextView mHistoryDetailCreateTime;
    @BindView(R.id.history_detail_create_name)
    TextView mHistoryDetailCreateName;
    @BindView(R.id.history_detail_update_time)
    TextView mHistoryDetailUpdateTime;
    @BindView(R.id.history_detail_web_view)
    WebView mHistoryDetailWebView;
    @BindView(R.id.history_expand_list_view)
    MyExpandableListView mHistoryExpandListView;
    @BindView(R.id.history_expand_material_list_view)
    MyExpandableListView mHistoryExpandMaterialListView;

    private ExpandableListAdapter mExpandableListAdapter;
    private MyMaterialExpandableAdapter mMyMaterialExpandableAdapter;

    //data
    private EmergencyListEntity mEmergencyListEntity;
    private HistoryDetailEngine mHistoryDetailEngine;
    private List<List<MaterialEntity>> mLists = new ArrayList<>();
    private int mClickIndex = 0;

    @Override
    protected int getViewId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHistoryDetailEngine = new HistoryDetailEngine(this, this);
        Intent _intent = getIntent();
        if (null != _intent) {
            mEmergencyListEntity = (EmergencyListEntity) _intent.getSerializableExtra(HISTORY_DETAIL_KEY);
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        String _historyTitle = "";
        if (null != mEmergencyListEntity) {
            _historyTitle = mEmergencyListEntity.getEmergencyTitle();
        }
        if (!TextUtils.isEmpty(_historyTitle)) {
            mTitleBar.setTitleBar(_historyTitle, R.mipmap.nav_btn_back, 0, this);
        } else {
            mTitleBar.setTitleBar(getString(R.string.emergencyDetailFragment_title), R.mipmap.nav_btn_back, 0, this);
        }
        mHistoryExpandMaterialListView.setOnGroupClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        if (null == mEmergencyListEntity) return;
        //紧急联系人手机号
        List<ExpandEntity> _expandEntityListGroup = new ArrayList<>();
        List<List<ExpandEntity>> _expandEntityListList = new ArrayList<>();
        String _contactPhone = mEmergencyListEntity.getContactMobile();
        String[] _phoneArray = AppUtils.getPhoneStringArray(_contactPhone);
        if (null != _phoneArray) {
            for (String _phone : _phoneArray) {
                _expandEntityListGroup.add(new ExpandEntity("现场联系人手机", _phone));
            }
        }
        //紧急联系人名字和地址
        String _contactName = mEmergencyListEntity.getContactName();
        String[] _nameArray = AppUtils.getPhoneStringArray(_contactName);
        if (null != _nameArray) {
            for (String _nameAndPlace : _nameArray) {
                String[] _nameAndPlaceArray = AppUtils.getNameAndPlace(_nameAndPlace);
                List<ExpandEntity> _expandEntityListChild = new ArrayList<>();
                _expandEntityListChild.add(new ExpandEntity("现场联系人", _nameAndPlaceArray[0]));
                _expandEntityListChild.add(new ExpandEntity("地点", _nameAndPlaceArray[1]));
                _expandEntityListList.add(_expandEntityListChild);
            }
        }
        mExpandableListAdapter = new MyExpandableAdapter(this, _expandEntityListGroup, _expandEntityListList);
        mHistoryExpandListView.setAdapter(mExpandableListAdapter);
        long _createTime = mEmergencyListEntity.getCreateTime();
        if (0 != _createTime) {
            String _createData = DateUtils.formatYMDHMA(_createTime);
            if (!TextUtils.isEmpty(_createData)) {
                mHistoryDetailCreateTime.setText(_createData);
            } else {
                mHistoryDetailCreateTime.setText("");
            }
        } else {
            mHistoryDetailCreateTime.setText("");
        }
        String _createName = mEmergencyListEntity.getUserName();
        if (!TextUtils.isEmpty(_createName)) {
            mHistoryDetailCreateName.setText("发起人：" + _createName);
        } else {
            mHistoryDetailCreateName.setText("发起人：");
        }
        long _updateTime = mEmergencyListEntity.getCreateTime();
        if (0 != _updateTime) {
            String _updateData = DateUtils.formatYMDHMA(_createTime);
            if (!TextUtils.isEmpty(_updateData)) {
                mHistoryDetailUpdateTime.setText(_updateData);
            } else {
                mHistoryDetailUpdateTime.setText("");
            }
        } else {
            mHistoryDetailUpdateTime.setText("");
        }
        String _stepStr = mEmergencyListEntity.getEventStepstr();
        if (!TextUtils.isEmpty(_stepStr)) {
            mHistoryDetailWebView.loadDataWithBaseURL("about:blank", _stepStr, "text/html", "utf-8", null);
        } else {
            mHistoryDetailWebView.setVisibility(View.GONE);
        }
        //获取材料等
        String _line = mEmergencyListEntity.getEmergencyCircuitNumber();
        String _stationStar = mEmergencyListEntity.getEmergencyCircuitStation();
        String _statingEnd = mEmergencyListEntity.getEmergencyCircuitStationEnd();
        List<String> _stationList = mHistoryDetailEngine.getMiddleStation(_line, _stationStar, _statingEnd);
        if (null != _stationList && 0 != _stationList.size()) {
            for (int i = 0; i < _stationList.size(); i++) {
                List<MaterialEntity> _materialList = new ArrayList<>();
                mLists.add(_materialList);
            }
            mMyMaterialExpandableAdapter = new MyMaterialExpandableAdapter(this, _stationList, mLists);
            mHistoryExpandMaterialListView.setAdapter(mMyMaterialExpandableAdapter);
        }
    }

    @Override
    public boolean onGroupClick(ExpandableListView _expandableListView, View _view, int _i, long _l) {
        //如果分组被打开 直接关闭
        if (mHistoryExpandMaterialListView.isGroupExpanded(_i)) {
            mHistoryExpandMaterialListView.collapseGroup(_i);
        } else {
            String _groupString = (String) mMyMaterialExpandableAdapter.getGroup(_i);
            if (!TextUtils.isEmpty(_groupString)) {
                mClickIndex = _i;
                mHistoryDetailEngine.getCailiaoInfo(_groupString);
            }
        }
        return true;
    }

    @Override
    public void leftOnClick() {
        finish();
    }

    @Override
    public void rightOnClick() {

    }

    @Override
    public void getCailiaoOnSuccess(List<MaterialEntity> _listEntities) {
        mLists.get(mClickIndex).clear();
        mLists.get(mClickIndex).addAll(_listEntities);
        mHistoryExpandMaterialListView.expandGroup(mClickIndex, true);
    }

    @Override
    public void getCailiaoOnError(String _str) {

    }

}
