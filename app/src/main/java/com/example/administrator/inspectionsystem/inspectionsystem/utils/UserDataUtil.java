package com.example.administrator.inspectionsystem.inspectionsystem.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionData;
import com.example.administrator.inspectionsystem.inspectionsystem.data.InspectionTable;

import java.util.ArrayList;
import java.util.List;

public class UserDataUtil {
    private InspectionData inspectionData;
    private static  List<User> list;
    public UserDataUtil(Context context){
        this.inspectionData = new InspectionData(context);
       }

    public static void creatUserData(SQLiteDatabase db){
        list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User adm = new User();
            adm.setRole(User.ROLE_ADMINISTRATOR);
            adm.setAccount("admin"+i);
            adm.setPassword("admin"+i);
            adm.setName("admin"+i);
            list.add(adm);
        }
        for (int i = 1; i <= 10; i++) {
            User ins = new User();
            ins.setRole(User.ROLE_INSPECTOR);
            ins.setAccount("ins"+i);
            ins.setPassword("ins"+i);
            ins.setName("inspector"+i);
            list.add(ins);
        }
        for (User user : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(InspectionTable.COL_USER_ACCOUNT,user.getAccount());
            contentValues.put(InspectionTable.COL_USER_NAME,user.getName());
            contentValues.put(InspectionTable.COL_USER_PASSWORD,user.getPassword());
            contentValues.put(InspectionTable.COL_USER_ROLE,user.getRole());
            db.insert(InspectionTable.TBL_NAME_USER,null,contentValues);
        }
    }

    public User getUserFromAccount(String account){
        SQLiteDatabase db = inspectionData.getReadableDatabase();
        Cursor cursor = db.query(InspectionTable.TBL_NAME_USER, null,InspectionTable.COL_USER_ACCOUNT+" = ? ", new String[]{account},null,null,null,null);
        User aim = new User();
        if(cursor.getCount() == 0){
            return null;
        }
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            aim.setRole(cursor.getInt(cursor.getColumnIndex(InspectionTable.COL_USER_ROLE)));
            aim.setName(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_USER_NAME)));
            aim.setPassword(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_USER_PASSWORD)));
            aim.setAccount(cursor.getString(cursor.getColumnIndex(InspectionTable.COL_USER_ACCOUNT)));
        }
        return aim;
    }
}
