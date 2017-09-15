package com.yuanye.njdt.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.millet.androidlib.Function.ZoomImage.ZoomImageActivity;
import com.millet.androidlib.UI.Adapter.GridViewImageAdapter;
import com.millet.androidlib.UI.CustomView.MyGridView;
import com.millet.androidlib.UI.CustomView.SquareImageView;
import com.millet.androidlib.UI.CustomView.TitleBar;
import com.millet.androidlib.UI.InterfaceUi.TitleBarClickListener;
import com.millet.androidlib.Utils.GlideUtils;
import com.millet.androidlib.Utils.ToastUtils;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumListener;
import com.yuanye.njdt.R;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.dao.PlanDao;
import com.yuanye.njdt.data.entity.PlanEntity;
import com.yuanye.njdt.presenter.engine.PublishEventEngine;
import com.yuanye.njdt.presenter.enginedelegate.PublishEventEngineDelegate;
import com.yuanye.njdt.ui.baseui.BaseModuleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by llay on 2017/9/10.
 */

public class PublishEventActivity extends BaseModuleActivity implements TitleBarClickListener, PublishEventEngineDelegate, View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String TAG = PublishEventActivity.class.getSimpleName();

    public static void launch(Context _context) {
        Intent _intent = new Intent(_context, PublishEventActivity.class);
        _context.startActivity(_intent);
    }

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.publish_event_title)
    EditText mPublishEventTitle;
    @BindView(R.id.publish_event_content)
    EditText mPublishEventContent;
    @BindView(R.id.publish_event_choose_plan)
    TextView mPublishEventChoosePlan;
    @BindView(R.id.plan_choose_image)
    SquareImageView mPlanChooseImage;
    @BindView(R.id.plan_image_choose)
    ImageView mPlanImageChoose;
    @BindView(R.id.image_grid_view)
    MyGridView mImageGridView;

    private GridViewImageAdapter mGridViewImageAdapter;

    //data
    private List<PlanEntity> mPlanEntityList;
    private List<String> mListLabelName = new ArrayList<>();
    private List<AlbumFile> mAlbumFilesChoosed = new ArrayList<>();

    private PublishEventEngine mPublishEventEngine;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPlanEntityList = PlanDao.queryAll();
        if (null != mPlanEntityList && 0 != mPlanEntityList.size()) {
            for (PlanEntity _planEntity : mPlanEntityList) {
                mListLabelName.add(_planEntity.getLabel());
            }
        }
        mPublishEventEngine = new PublishEventEngine(this, this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_publish_event;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mTitleBar.setTitleBar(getString(R.string.publish_event_title), R.mipmap.nav_btn_back, 0, this);
        mPublishEventChoosePlan.setOnClickListener(this);
        mPlanImageChoose.setOnClickListener(this);
        mImageGridView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View _view) {
        int _id = _view.getId();
        switch (_id) {
            //处理
            case R.id.publish_event_choose_plan:
                if (null == mListLabelName || 0 == mListLabelName.size()) {
                    ToastUtils.showToast(this, "暂无数据", 0);
                    return;
                }
                showItemChooseSingle(mListLabelName, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (!TextUtils.isEmpty(text)) {
                            mPublishEventChoosePlan.setText(text);
                        } else {
                            mPublishEventChoosePlan.setText("");
                        }
                        if (null != mPlanEntityList) {
                            final String _url = ApiConfig.PIC_URL_PRE + mPlanEntityList.get(position).getImgurl();
                            if (!TextUtils.isEmpty(_url)) {
                                GlideUtils.loadImageView(PublishEventActivity.this, _url, mPlanChooseImage);
                                mPlanChooseImage.setVisibility(View.VISIBLE);
                                mPlanChooseImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View _view) {
                                        ZoomImageActivity.launch(PublishEventActivity.this, _url);
                                    }
                                });
                            } else {
                                mPlanChooseImage.setVisibility(View.GONE);
                            }
                        }
                    }
                });
                break;
            //选择照片
            case R.id.plan_image_choose:
                Album.image(this) // 选择图片。
                        .multipleChoice()
                        .requestCode(200)
                        .camera(true)
                        .columnCount(2)
                        .selectCount(3)
                        .checkedList((ArrayList<AlbumFile>) mAlbumFilesChoosed)
                        .listener(new AlbumListener<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAlbumResult(int requestCode, @NonNull final ArrayList<AlbumFile> result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mGridViewImageAdapter = new GridViewImageAdapter(PublishEventActivity.this);
                                        mGridViewImageAdapter.setData(result);
                                        mImageGridView.setAdapter(mGridViewImageAdapter);
                                        mAlbumFilesChoosed.clear();
                                        mAlbumFilesChoosed = result;
                                    }
                                });
                            }

                            @Override
                            public void onAlbumCancel(int requestCode) {

                            }
                        })
                        .start();
                break;
            default:
                break;
        }
    }

    @Override
    public void leftOnClick() {
        finish();
    }

    @Override
    public void rightOnClick() {

    }

    @Override
    public void onItemClick(AdapterView<?> _adapterView, View _view, int _i, long _l) {
        // 浏览选中的AlbumFile：
        Album.galleryAlbum(this).requestCode(2) // 请求码，会在listener中返回。
                .checkedList((ArrayList<AlbumFile>) mAlbumFilesChoosed)// 要浏览的图片列表：ArrayList<String>。
                .navigationAlpha(80) // Android5.0+的虚拟导航栏的透明度。
                .checkable(true) // 是否有浏览时的选择功能。
                .currentPosition(_i)
                .listener(new AlbumListener<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAlbumResult(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                        mGridViewImageAdapter.setData(result);
                        mAlbumFilesChoosed.clear();
                        mAlbumFilesChoosed = result;
                    }

                    @Override
                    public void onAlbumCancel(int requestCode) {

                    }
                })
                .start(); // 千万不要忘记调用start()方法。;
    }

}
