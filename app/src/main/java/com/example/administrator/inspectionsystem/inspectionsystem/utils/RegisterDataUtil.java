package com.example.administrator.inspectionsystem.inspectionsystem.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.inspectionsystem.inspectionsystem.bean.Register;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionData;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionTable;

import java.util.ArrayList;
import java.util.List;

public class RegisterDataUtil {

    private InspectionData inspectionData;
    public RegisterDataUtil(Context context){
        this.inspectionData = new InspectionData(context);
    }

    public void add(Register register){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InspectionTable.COL_REGISTER_DEVICE_ID,register.getDeviceId());
        contentValues.put(InspectionTable.COL_REGISTER_OPERATOR_ACCOUNT,register.getOperatorAccount());
        contentValues.put(InspectionTable.COL_REGISTER_PRESSURE,register.getPressure());
        contentValues.put(InspectionTable.COL_REGISTER_TIME,register.getTime());
        contentValues.put(InspectionTable.COL_REGISTER_TEMPERATURE,register.getTemperature());
        contentValues.put(InspectionTable.COL_REGISTER_OPERATOR_NAME,register.getOperatorName());
        contentValues.put(InspectionTable.COL_REGISTER_COMMENT,register.getComment());
        db.insert(InspectionTable.TBL_NAME_REGISTER,null,contentValues);
    }

    public List<Register> getRegisters(int deviceId){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        Cursor cursor;
        cursor = db.query(InspectionTable.TBL_NAME_REGISTER,null,InspectionTable.COL_REGISTER_DEVICE_ID+" = ? ", new String[]{String.valueOf(deviceId)},null,null,null,null);
        List<Register> list = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Register aim = new Register();
            aim.setId(cursor.getInt(cursor.getColumnIndex(InspectionTable.COL_REGISTER_ID)));
            aim.setDeviceId(cursor.getInt(cursor.getColumnIndex(InspectionTable.COL_REGISTER_DEVICE_ID)));
            aim.setOperatorAccount(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_REGISTER_OPERATOR_ACCOUNT)));
            aim.setOperatorName(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_REGISTER_OPERATOR_NAME)));
            aim.setTemperature(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_REGISTER_TEMPERATURE)));
            aim.setPressure(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_REGISTER_PRESSURE)));
            aim.setTime(cursor.getLong(cursor.getColumnIndex(InspectionTable.COL_REGISTER_TIME)));
            aim.setComment(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_REGISTER_COMMENT)));
            list.add(aim);
        }
        return list;
    }

}
