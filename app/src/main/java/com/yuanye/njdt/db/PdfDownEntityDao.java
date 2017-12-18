package com.yuanye.njdt.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yuanye.njdt.data.entity.PdfDownEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PDF_DOWN_ENTITY".
*/
public class PdfDownEntityDao extends AbstractDao<PdfDownEntity, String> {

    public static final String TABLENAME = "PDF_DOWN_ENTITY";

    /**
     * Properties of entity PdfDownEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property PdfName = new Property(0, String.class, "pdfName", true, "PDF_NAME");
    }


    public PdfDownEntityDao(DaoConfig config) {
        super(config);
    }
    
    public PdfDownEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PDF_DOWN_ENTITY\" (" + //
                "\"PDF_NAME\" TEXT PRIMARY KEY NOT NULL );"); // 0: pdfName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PDF_DOWN_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PdfDownEntity entity) {
        stmt.clearBindings();
 
        String pdfName = entity.getPdfName();
        if (pdfName != null) {
            stmt.bindString(1, pdfName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PdfDownEntity entity) {
        stmt.clearBindings();
 
        String pdfName = entity.getPdfName();
        if (pdfName != null) {
            stmt.bindString(1, pdfName);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public PdfDownEntity readEntity(Cursor cursor, int offset) {
        PdfDownEntity entity = new PdfDownEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // pdfName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PdfDownEntity entity, int offset) {
        entity.setPdfName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final String updateKeyAfterInsert(PdfDownEntity entity, long rowId) {
        return entity.getPdfName();
    }
    
    @Override
    public String getKey(PdfDownEntity entity) {
        if(entity != null) {
            return entity.getPdfName();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PdfDownEntity entity) {
        return entity.getPdfName() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
