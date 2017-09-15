package com.yuanye.njdt.dao;

import com.yuanye.njdt.MyApplication;
import com.yuanye.njdt.data.entity.ExampleEntity;

import java.util.List;

/**
 * Created by llay on 2017/9/11.
 */

public class ExampleDao {

    /**
     * 添加单个数据，如果有重复则覆盖
     *
     * @param _exampleEntity
     */
    public static void insertExampleEntity(ExampleEntity _exampleEntity) {
        MyApplication.getDaoInstant().getExampleEntityDao().insertOrReplace(_exampleEntity);
    }

    /**
     * 添加多个数据，如果有重复则覆盖
     *
     * @param _exampleEntityList
     */
    public static void insertExampleEntityList(List<ExampleEntity> _exampleEntityList) {
        MyApplication.getDaoInstant().getExampleEntityDao().insertOrReplaceInTx(_exampleEntityList);
    }

    /**
     * 查询全部数据
     */
    public static List<ExampleEntity> queryAll() {
        return MyApplication.getDaoInstant().getExampleEntityDao().loadAll();
    }

    /**
     * 删除所有数据
     */
    public static void deleteAll() {
        MyApplication.getDaoInstant().getExampleEntityDao().deleteAll();
    }

}
