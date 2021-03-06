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
import com.yuanye.njdt.dao.ExampleDao;
import com.yuanye.njdt.data.entity.ExampleEntity;
import com.yuanye.njdt.presenter.engine.ExampleFragmentEngine;
import com.yuanye.njdt.presenter.enginedelegate.ExampleFragmentEngineDelegate;
import com.yuanye.njdt.ui.adapter.ExampleListAdapter;
import com.yuanye.njdt.ui.baseui.BaseModuleFragment;
import com.yuanye.njdt.ui.callback.PdfDownInfoListener;
import com.yuanye.njdt.ui.callback.PdfDownStateListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ExampleFragment extends BaseModuleFragment implements TitleBarClickListener, ExampleFragmentEngineDelegate, PdfDownInfoListener, AdapterView.OnItemClickListener {

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.example_pull_list_view)
    PullToRefreshListView mExamplePullListView;

    private ExampleFragmentEngine mExampleFragmentEngine;
    private ExampleListAdapter mExampleListAdapter;
    private List<ExampleEntity> mExampleEntities;

    @Override
    protected int getViewId() {
        return R.layout.fragment_example_mian;
    }

    @Override
    protected void initData(ViewGroup container, Bundle savedInstanceState) {
        mExampleFragmentEngine = new ExampleFragmentEngine(getActivity(), this);
    }

    @Override
    protected void initViews(ViewGroup container, Bundle savedInstanceState, View _view) {
        mTitleBar.setTitleBar(getString(R.string.exampleFragment_title), 0, 0, this);
        mExamplePullListView.setMode(PullToRefreshBase.Mode.DISABLED);
        mExampleListAdapter = new ExampleListAdapter(getActivity());
        mExampleListAdapter.setPdfDownInfoListener(this);
        mExamplePullListView.setAdapter(mExampleListAdapter);
        mExamplePullListView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData(ViewGroup container, Bundle savedInstanceState) {
        mExampleEntities = ExampleDao.queryAll();
        if (null == mExampleEntities || 0 == mExampleEntities.size()) {
            if (null != mExampleFragmentEngine) {
                mExampleFragmentEngine.getExampleList();
            }
        } else {
            mExampleListAdapter.setData(mExampleEntities);
        }
    }

    @Override
    public void leftOnClick() {

    }

    @Override
    public void rightOnClick() {

    }

    @Override
    public void exampleListOnSuccess(List<ExampleEntity> _exampleEntityList) {
        if (null != _exampleEntityList) {
            mExampleListAdapter.setData(_exampleEntityList);
        }
    }

    @Override
    public void exampleListOnError(String _string) {
        ToastUtils.showToast(getActivity(), _string, 0);
    }

    @Override
    public void pdfDownInfo(String _value) {
        if (TextUtils.isEmpty(_value)) return;
        String _pdfUrl = ApiConfig.IP + _value;
        String _pdfName = AppUtils.getLastString(_value);
        String _fileDir = AppUtils.getFilePath();
        final MaterialDialog.Builder _materialDialog = new MaterialDialog.Builder(getActivity());
        _materialDialog.content("下载中");
        _materialDialog.autoDismiss(true);
        _materialDialog.progress(false, 100);
        final MaterialDialog _materialDialog1 = _materialDialog.build();
        mExampleFragmentEngine.planDownPdf(_pdfUrl, _fileDir, _pdfName, new PdfDownStateListener() {
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
                mExampleListAdapter.notifyDataSetChanged();
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
        ExampleEntity _exampleEntity = (ExampleEntity) mExampleListAdapter.getItem(_i - 1);
        if (null == _exampleEntity) return;
        String _fileNameAll = _exampleEntity.getDownloadUrl();
        if (TextUtils.isEmpty(_fileNameAll)) return;
        String _fileName = AppUtils.getLastString(_fileNameAll);
        String _path = AppUtils.getFilePath(_fileName);
        if (!TextUtils.isEmpty(_path)) {
            Intent _intent = FileUtils.openFile(_path);
            startActivity(_intent);
        } else {
            ToastUtils.showToast(getActivity(), "请先下载！", 0);
        }

    }

}
