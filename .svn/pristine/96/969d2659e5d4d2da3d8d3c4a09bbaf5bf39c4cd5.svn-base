package com.yuanye.njdt.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.millet.androidlib.UI.CustomView.TitleBar;
import com.millet.androidlib.UI.InterfaceUi.TitleBarClickListener;
import com.millet.androidlib.Utils.FileUtils;
import com.millet.androidlib.Utils.ToastUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.constants.AppUtils;
import com.yuanye.njdt.dao.PlanDao;
import com.yuanye.njdt.data.entity.PlanEntity;
import com.yuanye.njdt.presenter.engine.PlanFragmentEngine;
import com.yuanye.njdt.presenter.enginedelegate.PlanFragmentEngineDelegate;
import com.yuanye.njdt.ui.adapter.PlanListAdapter;
import com.yuanye.njdt.ui.baseui.BaseModuleFragment;
import com.yuanye.njdt.ui.callback.PdfDownInfoListener;
import com.yuanye.njdt.ui.callback.PdfDownStateListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class PlanFragment extends BaseModuleFragment implements TitleBarClickListener, PlanFragmentEngineDelegate, PdfDownInfoListener, AdapterView.OnItemClickListener {

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.plan_pull_list_view)
    PullToRefreshListView mPlanPullListView;

    private PlanFragmentEngine mPlanFragmentEngine;
    private PlanListAdapter mPlanListAdapter;
    private List<PlanEntity> mPlanEntities;

    @Override
    protected int getViewId() {
        return R.layout.fragment_plan_mian;
    }

    @Override
    protected void initData(ViewGroup container, Bundle savedInstanceState) {
        mPlanFragmentEngine = new PlanFragmentEngine(getActivity(), this);
    }

    @Override
    protected void initViews(ViewGroup container, Bundle savedInstanceState, View _view) {
        mTitleBar.setTitleBar(getString(R.string.planFragment_title), 0, R.mipmap.nav_btn_search, this);
        mPlanPullListView.setMode(PullToRefreshBase.Mode.DISABLED);
        mPlanListAdapter = new PlanListAdapter(getActivity());
        mPlanListAdapter.setPdfDownInfoListener(this);
        mPlanPullListView.setAdapter(mPlanListAdapter);
        mPlanPullListView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData(ViewGroup container, Bundle savedInstanceState) {
        mPlanEntities = PlanDao.queryAll();
        if (null == mPlanEntities || 0 == mPlanEntities.size()) {
            if (null != mPlanFragmentEngine) {
                mPlanFragmentEngine.getPlanList();
            }
        } else {
            mPlanListAdapter.setData(mPlanEntities);
        }
    }

    @Override
    public void leftOnClick() {

    }

    @Override
    public void rightOnClick() {

    }

    @Override
    public void PlanListOnSuccess(List<PlanEntity> _planEntityList) {
        if (null != _planEntityList) {
            mPlanListAdapter.setData(_planEntityList);
        }
    }

    @Override
    public void PlanListOnError(String _string) {
        ToastUtils.showToast(getActivity(), _string, 0);
    }

    @Override
    public void pdfDownInfo(String _value) {
        if (TextUtils.isEmpty(_value)) return;
        String _pdfUrl = ApiConfig.PDF_URL_PRE + _value + ApiConfig.PDF_URL_SUFF;
        String _pdfName = _value + ApiConfig.PDF_URL_SUFF;
        String _fileDir = AppUtils.getFilePath();
        final MaterialDialog.Builder _materialDialog = new MaterialDialog.Builder(getActivity());
        _materialDialog.content("下载中");
        _materialDialog.autoDismiss(true);
        _materialDialog.progress(false, 100);
        final MaterialDialog _materialDialog1 = _materialDialog.build();
        mPlanFragmentEngine.planDownPdf(_pdfUrl, _fileDir, _pdfName, new PdfDownStateListener() {
            @Override
            public void onPdfResultStart() {
                _materialDialog1.show();
            }

            @Override
            public void onPdfResultProgress(int _progress) {
                _materialDialog1.setProgress(_progress);
            }

            @Override
            public void onPdfResultEnd() {
                mPlanListAdapter.notifyDataSetChanged();
                _materialDialog1.dismiss();
            }

            @Override
            public void onPdfResultFailure(String _result) {
                ToastUtils.showToast(getActivity(), _result, 0);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> _adapterView, View _view, int _i, long _l) {
        PlanEntity _planEntity = (PlanEntity) mPlanListAdapter.getItem(_i - 1);
        if (null == _planEntity) return;
        String _fileName = _planEntity.getValue() + ApiConfig.PDF_URL_SUFF;
        String _path = AppUtils.getFilePath(_fileName);
        if (!TextUtils.isEmpty(_path)) {
            Intent _intent = FileUtils.openFile(_path);
            startActivity(_intent);
        } else {
            ToastUtils.showToast(getActivity(), "请先下载！", 0);
        }
    }

}
