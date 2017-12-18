package com.yuanye.njdt.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.millet.androidlib.UI.CustomView.TitleBar;
import com.millet.androidlib.UI.InterfaceUi.TitleBarClickListener;
import com.millet.androidlib.Utils.ToastUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.data.entity.EmergencyListEntity;
import com.yuanye.njdt.presenter.engine.HistoryFragmentEngine;
import com.yuanye.njdt.presenter.enginedelegate.HistoryFragmentEngineDelegate;
import com.yuanye.njdt.ui.activity.HistoryDetailActivity;
import com.yuanye.njdt.ui.adapter.HistoryListAdapter;
import com.yuanye.njdt.ui.baseui.BaseModuleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class HistoryFragment extends BaseModuleFragment implements TitleBarClickListener, HistoryFragmentEngineDelegate, PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.history_pull_list_view)
    PullToRefreshListView mHistoryPullListView;

    private HistoryFragmentEngine mHistoryFragmentEngine;
    private HistoryListAdapter mHistoryListAdapter;
    private int mIndex = 0;
    private List<EmergencyListEntity> mEmergencyListEntities;

    @Override
    protected int getViewId() {
        return R.layout.fragment_history_mian;
    }

    @Override
    protected void initData(ViewGroup container, Bundle savedInstanceState) {
        mEmergencyListEntities = new ArrayList<>();
        mHistoryFragmentEngine = new HistoryFragmentEngine(getActivity(), this);
    }

    @Override
    protected void initViews(ViewGroup container, Bundle savedInstanceState, View _view) {
        mTitleBar.setTitleBar(getString(R.string.historyFragment_title), 0, 0, this);
        mHistoryPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        mHistoryListAdapter = new HistoryListAdapter(getActivity());
        mHistoryPullListView.setOnRefreshListener(this);
        mHistoryPullListView.setAdapter(mHistoryListAdapter);
        mHistoryPullListView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData(ViewGroup container, Bundle savedInstanceState) {
        if (null != mHistoryFragmentEngine) {
            mHistoryFragmentEngine.getHistoryList(0);
        }
    }

    @Override
    public void leftOnClick() {

    }

    @Override
    public void rightOnClick() {

    }

    @Override
    public void HistoryListOnSuccess(List<EmergencyListEntity> _emergencyListEntities) {
        mHistoryPullListView.onRefreshComplete();
        if (null != _emergencyListEntities) {
            mEmergencyListEntities.addAll(_emergencyListEntities);
            mHistoryListAdapter.setData(mEmergencyListEntities);
        }
    }

    @Override
    public void HistoryListOnError(String _string) {
        mHistoryPullListView.onRefreshComplete();
        ToastUtils.showToast(getActivity(), _string, 0);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (null != mHistoryFragmentEngine) {
            mIndex = 0;
            mEmergencyListEntities.clear();
            mHistoryFragmentEngine.getHistoryList(0);
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (null != mHistoryFragmentEngine) {
            mHistoryFragmentEngine.getHistoryList(++mIndex);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> _adapterView, View _view, int _i, long _l) {
        EmergencyListEntity _emergencyListEntity = (EmergencyListEntity) mHistoryListAdapter.getItem(_i - 1);
        if (null != _emergencyListEntity) {
            HistoryDetailActivity.launch(getActivity(), _emergencyListEntity);
        }
    }

}
