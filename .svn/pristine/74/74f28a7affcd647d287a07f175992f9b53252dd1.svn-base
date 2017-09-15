package com.yuanye.njdt.dao;

import com.yuanye.njdt.MyApplication;
import com.yuanye.njdt.data.entity.PdfDownEntity;
import com.yuanye.njdt.db.PdfDownEntityDao;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class PdfDownDao {

    /**
     * 添加单个数据，如果有重复则覆盖
     *
     * @param _pdfDownEntity
     */
    public static void insertPdfEntity(PdfDownEntity _pdfDownEntity) {
        MyApplication.getDaoInstant().getPdfDownEntityDao().insertOrReplace(_pdfDownEntity);
    }

    /**
     * 返回单个数据
     *
     * @param _str
     * @return
     */
    public static PdfDownEntity queryEntity(String _str) {
        return MyApplication.getDaoInstant().getPdfDownEntityDao().queryBuilder().where(PdfDownEntityDao.Properties.PdfName.eq(_str)).unique();
    }

    /**
     * 删除某个元素
     *
     * @param _pdfDownEntity
     */
    public static void deletePdfEntity(PdfDownEntity _pdfDownEntity) {
        MyApplication.getDaoInstant().getPdfDownEntityDao().delete(_pdfDownEntity);
    }

    /**
     * 删除某个元素
     *
     * @param _string
     */
    public static void deletePdfEntity(String _string) {
        MyApplication.getDaoInstant().getPdfDownEntityDao().deleteByKey(_string);
    }

}
