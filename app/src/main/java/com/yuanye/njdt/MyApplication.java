package com.yuanye.njdt;


import android.database.sqlite.SQLiteDatabase;

import com.millet.androidlib.Base.BaseApplication;
import com.yuanye.njdt.db.DaoMaster;
import com.yuanye.njdt.db.DaoSession;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class MyApplication extends BaseApplication {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库njdt.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "njdt.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

}
