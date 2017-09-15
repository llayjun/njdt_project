package com.yuanye.njdt.ui.view;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.millet.androidlib.R;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class NotificationUtil {

    private Context mContext;
    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;

    public NotificationUtil(Context _context) {
        this.mContext = _context;
        initNotification();
    }

    /**
     * 初始化通知栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initNotification() {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(mContext);
        mBuilder.setContentTitle("正在更新...");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setDefaults(Notification.DEFAULT_LIGHTS);//设置通知的提醒方式： 呼吸灯
        mBuilder.setContentText("下载进度:" + "0%");
        mBuilder.setProgress(100, 0, false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setProgress(int _progress) {
        mBuilder.setProgress(100, _progress, false);
        mBuilder.setContentText("下载进度:" + _progress + "%");
        mNotificationManager.notify(1, mBuilder.build());
    }

    /**
     * 影藏通知
     */
    public void hideProgress() {
        mNotificationManager.cancel(1);
    }

}
