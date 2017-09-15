package com.yuanye.njdt.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.yuanye.njdt.data.entity.PlanEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PLAN_ENTITY".
*/
public class PlanEntityDao extends AbstractDao<PlanEntity, Void> {

    public static final String TABLENAME = "PLAN_ENTITY";

    /**
     * Properties of entity PlanEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", false, "ID");
        public final static Property Value = new Property(1, String.class, "value", false, "VALUE");
        public final static Property Label = new Property(2, String.class, "label", false, "LABEL");
        public final static Property Imgurl = new Property(3, String.class, "imgurl", false, "IMGURL");
    }


    public PlanEntityDao(DaoConfig config) {
        super(config);
    }
    
    public PlanEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLAN_ENTITY\" (" + //
                "\"ID\" INTEGER NOT NULL ," + // 0: id
                "\"VALUE\" TEXT," + // 1: value
                "\"LABEL\" TEXT," + // 2: label
                "\"IMGURL\" TEXT);"); // 3: imgurl
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAN_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PlanEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(2, value);
        }
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(3, label);
        }
 
        String imgurl = entity.getImgurl();
        if (imgurl != null) {
            stmt.bindString(4, imgurl);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PlanEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(2, value);
        }
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(3, label);
        }
 
        String imgurl = entity.getImgurl();
        if (imgurl != null) {
            stmt.bindString(4, imgurl);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public PlanEntity readEntity(Cursor cursor, int offset) {
        PlanEntity entity = new PlanEntity( //
            cursor.getInt(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // value
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // label
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // imgurl
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PlanEntity entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setValue(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLabel(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImgurl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(PlanEntity entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(PlanEntity entity) {
        return null;
    }

    @Override
    public boolean hasKey(PlanEntity entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
