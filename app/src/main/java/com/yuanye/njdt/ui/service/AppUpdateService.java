package com.yuanye.njdt.ui.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.stetho.common.LogUtil;
import com.millet.androidlib.Utils.HandlerUtil;
import com.millet.androidlib.Utils.SDCardUtils;
import com.yuanye.njdt.presenter.service.IReqResultDownFileCallBack;
import com.yuanye.njdt.presenter.service.ServiceManager;
import com.yuanye.njdt.ui.view.NotificationUtil;

import java.io.File;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class AppUpdateService extends IntentService {

    private static final String TAG = AppUpdateService.class.getSimpleName();

    public static final String APP_FILE_NAME = "njdt_app.apk";
    public static final String UPDATE_URL = "UPDATE_URL";

    private NotificationUtil mNotificationUtil;

    public static void launchUpdateService(Context _context, String _url) {
        Intent _intent = new Intent(_context, AppUpdateService.class);
        _intent.putExtra(UPDATE_URL, _url);
        _context.startService(_intent);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public AppUpdateService() {
        super("AppUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            if (null != intent) {
                String _updateUrl = null;//下载链接
                String _updatePath;//包地址
                if (intent.hasExtra(UPDATE_URL)) {
                    _updateUrl = intent.getStringExtra(UPDATE_URL);
                }
                if (TextUtils.isEmpty(_updateUrl)) return;
                _updatePath = getFilePath();
                ServiceManager.getInstance().getIMyService().reqDownFileWithNoParam(_updateUrl, _updatePath, APP_FILE_NAME, new IReqResultDownFileCallBack() {
                    @Override
                    public void onResultStart() {
                        mNotificationUtil = new NotificationUtil(AppUpdateService.this);
                    }

                    @Override
                    public void onResultProgress(int _progress) {
                        System.out.println(_progress);
                        mNotificationUtil.setProgress(_progress);
                    }

                    @Override
                    public void onResultEnd() {
                        mNotificationUtil.hideProgress();
                        HandlerUtil.getMainHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                installApk();
                            }
                        });
                    }

                    @Override
                    public void onResultFailure(String _result) {

                    }
                });
            }
        } catch (Exception _e) {
            LogUtil.e(TAG, _e);
        }
    }

    private String getFilePath() {
        try {
            String fileName = "down/";
            String filePath = SDCardUtils.createSubDir(fileName).getAbsolutePath();
            return filePath;
        } catch (Exception _e) {
            LogUtil.e(TAG, _e);
            return null;
        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        try {
            File apkfile = new File(getFilePath(), APP_FILE_NAME);
            if (!apkfile.exists()) {
                return;
            }
            // 通过Intent安装APK文件
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            this.startActivity(intent);
        } catch (Exception _e) {
            LogUtil.e(TAG, _e);
        }
    }

}
