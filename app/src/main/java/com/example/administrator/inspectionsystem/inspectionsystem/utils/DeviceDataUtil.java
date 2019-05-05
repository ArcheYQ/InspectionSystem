package com.example.administrator.inspectionsystem.inspectionsystem.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionData;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionTable;

import java.util.ArrayList;
import java.util.List;


public class DeviceDataUtil {
    private InspectionData inspectionData;
    public DeviceDataUtil(Context context){
        this.inspectionData = new InspectionData(context);
    }
    public void addDevice(Device device){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InspectionTable.COL_DEVICE_ADDTIME, device.getAddTime());
        contentValues.put(InspectionTable.COL_DEVICE_LOCATION, device.getLocation());
        contentValues.put(InspectionTable.COL_DEVICE_ADMACCOUNT, device.getAdmAccount());
        contentValues.put(InspectionTable.COL_DEVICE_ISPUBLISH, device.getIsPublic());
        contentValues.put(InspectionTable.COL_DEVICE_NAME, device.getName());
        contentValues.put(InspectionTable.COL_DEVICE_NUMBER, device.getNumber());
        db.insert(InspectionTable.TBL_NAME_DEVICE,null,contentValues);
    }
    public List<Device> getDevices(boolean isAdm){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        Cursor cursor;
        if (isAdm){
            cursor = db.query(InspectionTable.TBL_NAME_DEVICE, null,null, null,null,null,null,null);
        }else{
            cursor = db.query(InspectionTable.TBL_NAME_DEVICE,null,InspectionTable.COL_DEVICE_ISPUBLISH+" = ? ", new String[]{1+""},null,null,null,null);
        }
        List<Device> list = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Device aim = new Device();
            aim.setId(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_DEVICE_ID)));
            aim.setAddTime(cursor.getLong(cursor.getColumnIndex(InspectionTable.COL_DEVICE_ADDTIME)));
            aim.setPublic(cursor.getInt(cursor.getColumnIndex(InspectionTable.COL_DEVICE_ISPUBLISH)));
            aim.setLocation(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_DEVICE_LOCATION)));
            aim.setNumber(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_DEVICE_NUMBER)));
            aim.setAdmAccount(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_DEVICE_ADMACCOUNT)));
            aim.setName(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_DEVICE_NAME)));
            list.add(aim);
        }
        return list;
    }
}
