package com.example.administrator.inspectionsystem.inspectionsystem.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.inspectionsystem.inspectionsystem.bean.Advice;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionData;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionTable;

import java.util.ArrayList;
import java.util.List;


public class AdviceDataUtil {
    private InspectionData inspectionData;
    public AdviceDataUtil(Context context){
        this.inspectionData = new InspectionData(context);
    }
    public void addAdvice(Advice advice){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InspectionTable.COL_ADVICE_ADDTIME,advice.getAddTime());
        contentValues.put(InspectionTable.COL_ADVICE_LOCATION,advice.getLocation());
        contentValues.put(InspectionTable.COL_ADVICE_ADMACCOUNT,advice.getAdmAccount());
        contentValues.put(InspectionTable.COL_ADVICE_ISPUBLISH,advice.getIsPublic());
        contentValues.put(InspectionTable.COL_ADVICE_NAME,advice.getName());
        contentValues.put(InspectionTable.COL_ADVICE_NUMBER,advice.getNumber());
        db.insert(InspectionTable.TBL_NAME_ADVICE,null,contentValues);
    }
    public List<Advice> getAdvices(boolean isAdm){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        Cursor cursor;
        if (isAdm){
            cursor = db.query(InspectionTable.TBL_NAME_ADVICE, null,null, null,null,null,null,null);
        }else{
            cursor = db.query(InspectionTable.TBL_NAME_ADVICE,null,InspectionTable.COL_ADVICE_ISPUBLISH+" = ? ", new String[]{1+""},null,null,null,null);
        }
        List<Advice> list = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Advice aim = new Advice();
            aim.setId(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_ADVICE_ID)));
            aim.setAddTime(cursor.getLong(cursor.getColumnIndex(InspectionTable.COL_ADVICE_ADDTIME)));
            aim.setPublic(cursor.getInt(cursor.getColumnIndex(InspectionTable.COL_ADVICE_ISPUBLISH)));
            aim.setLocation(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_ADVICE_LOCATION)));
            aim.setNumber(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_ADVICE_NUMBER)));
            aim.setAdmAccount(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_ADVICE_ADMACCOUNT)));
            aim.setName(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_ADVICE_NAME)));
            list.add(aim);
        }
        return list;
    }
}
