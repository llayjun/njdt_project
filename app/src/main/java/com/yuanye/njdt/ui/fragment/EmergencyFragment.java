package com.yuanye.njdt.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.millet.androidlib.Utils.ToastUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.data.entity.EmergencyListEntity;
import com.yuanye.njdt.presenter.engine.EmergencyFragmentEngine;
import com.yuanye.njdt.presenter.enginedelegate.EmergencyFragmentEngineDelegate;
import com.yuanye.njdt.ui.activity.ChangePassActivity;
import com.yuanye.njdt.ui.activity.EmergencyDetailActivity;
import com.yuanye.njdt.ui.activity.PublishEventActivity;
import com.yuanye.njdt.ui.adapter.EmergencyListAdapter;
import com.yuanye.njdt.ui.baseui.BaseModuleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class EmergencyFragment extends BaseModuleFragment implements View.OnClickListener, EmergencyFragmentEngineDelegate, PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    @BindView(R.id.head_left_image)
    ImageView mHeadLeftImage;
    @BindView(R.id.header_htv_subtitle)
    TextView mHeaderHtvSubtitle;
    @BindView(R.id.header_title)
    LinearLayout mHeaderTitle;
    @BindView(R.id.head_first_right_image)
    ImageView mHeadFirstRightImage;
    @BindView(R.id.emergency_pull_list_view)
    PullToRefreshListView mEmergencyPullListView;

    private EmergencyFragmentEngine mEmergencyFragmentEngine;
    private EmergencyListAdapter mEmergencyListAdapter;
    private int mIndex = 0;
    private List<EmergencyListEntity> mEmergencyListEntities;

    @Override
    protected int getViewId() {
        return R.layout.fragment_emergency_mian;
    }

    @Override
    protected void initData(ViewGroup container, Bundle savedInstanceState) {
        mEmergencyListEntities = new ArrayList<>();
        mEmergencyFragmentEngine = new EmergencyFragmentEngine(getActivity(), this);
    }

    @Override
    protected void initViews(ViewGroup container, Bundle savedInstanceState, View _view) {
        mHeadLeftImage.setImageResource(R.mipmap.nav_btn_password);
        mHeadLeftImage.setOnClickListener(this);
        mHeaderHtvSubtitle.setText(getString(R.string.emergencyFragment_title));
        mHeadFirstRightImage.setImageResource(R.mipmap.nav_btn_edit);
        mHeadFirstRightImage.setOnClickListener(this);
        mEmergencyPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        mEmergencyListAdapter = new EmergencyListAdapter(getActivity());
        mEmergencyPullListView.setOnRefreshListener(this);
        mEmergencyPullListView.setAdapter(mEmergencyListAdapter);
        mEmergencyPullListView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData(ViewGroup container, Bundle savedInstanceState) {
        if (null != mEmergencyFragmentEngine) {
            mEmergencyFragmentEngine.getEmergencyList(0);
        }
    }

    @Override
    public void onClick(View view) {
        int _id = view.getId();
        if (_id == R.id.head_left_image) {
            ChangePassActivity.launch(getActivity());
        } else if (_id == R.id.head_first_right_image) {
            PublishEventActivity.launch(getActivity());
        }
    }

    @Override
    public void EmergencyListOnSuccess(List<EmergencyListEntity> _emergencyListEntities) {
        mEmergencyPullListView.onRefreshComplete();
        if (null != _emergencyListEntities) {
            mEmergencyListEntities.addAll(_emergencyListEntities);
            mEmergencyListAdapter.setData(mEmergencyListEntities);
        }
    }

    @Override
    public void EmergencyListOnError(String _string) {
        mEmergencyPullListView.onRefreshComplete();
        ToastUtils.showToast(getActivity(), _string, 0);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (null != mEmergencyFragmentEngine) {
            mIndex = 0;
            mEmergencyListEntities.clear();
            mEmergencyFragmentEngine.getEmergencyList(0);
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (null != mEmergencyFragmentEngine) {
            mEmergencyFragmentEngine.getEmergencyList(++mIndex);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> _adapterView, View _view, int _i, long _l) {
        EmergencyListEntity _emergencyListEntity = (EmergencyListEntity) mEmergencyListAdapter.getItem(_i - 1);
        if (null != _emergencyListEntity) {
            EmergencyDetailActivity.launch(getActivity(), _emergencyListEntity);
        }
    }

}
