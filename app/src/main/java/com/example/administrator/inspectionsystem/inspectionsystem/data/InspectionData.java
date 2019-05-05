package com.example.administrator.inspectionsystem.inspectionsystem.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;

public class InspectionData extends SQLiteOpenHelper {
    private static final String DB_NAME = "INSPECTION2.DB";
    private static final int DB_VERSION = 1;
    public InspectionData (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public InspectionData (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public InspectionData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String NewSQL = InspectionTable.getCreatUSERSQL();
        db.execSQL(NewSQL);
        NewSQL = InspectionTable.getCreatDEVICESQL();
        db.execSQL(NewSQL);
        UserDataUtil.createUserData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
