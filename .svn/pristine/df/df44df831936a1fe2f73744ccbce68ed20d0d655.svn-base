package com.yuanye.njdt.dao;

import com.yuanye.njdt.MyApplication;
import com.yuanye.njdt.data.entity.PlanEntity;
import com.yuanye.njdt.db.PlanEntityDao;

import java.util.List;

/**
 * Created by llay on 2017/9/11.
 */

public class PlanDao {

    /**
     * 添加单个数据，如果有重复则覆盖
     *
     * @param _planEntity
     */
    public static void insertPlanEntity(PlanEntity _planEntity) {
        MyApplication.getDaoInstant().getPlanEntityDao().insertOrReplace(_planEntity);
    }

    /**
     * 添加多个数据，如果有重复则覆盖
     *
     * @param _planEntityList
     */
    public static void insertPlanEntityList(List<PlanEntity> _planEntityList) {
        MyApplication.getDaoInstant().getPlanEntityDao().insertOrReplaceInTx(_planEntityList);
    }

    /**
     * 查找_id的数据
     *
     * @param _id
     * @return
     */
    public static PlanEntity selectPlanEntity(int _id) {
        return MyApplication.getDaoInstant().getPlanEntityDao().queryBuilder().where(PlanEntityDao.Properties.Id.eq(_id)).unique();
    }

    /**
     * 查询全部数据
     */
    public static List<PlanEntity> queryAll() {
        return MyApplication.getDaoInstant().getPlanEntityDao().loadAll();
    }

    /**
     * 删除所有数据
     */
    public static void deleteAll() {
        MyApplication.getDaoInstant().getPlanEntityDao().deleteAll();
    }

}
