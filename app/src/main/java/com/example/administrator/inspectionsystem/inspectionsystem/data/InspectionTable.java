package com.example.administrator.inspectionsystem.inspectionsystem.data;

public class InspectionTable {

    public static final String TBL_NAME_USER = "USER";
    public static final String COL_USER_ID = "USERID";
    public static final String COL_USER_ACCOUNT = "ACCOUNT";
    public static final String COL_USER_PASSWORD= "PASSWORD";
    public static final String COL_USER_ROLE = "ROLE";
    public static final String COL_USER_NAME = "NAME";

    public static final String TBL_NAME_DEVICE = "DEVICE";
    public static final String COL_DEVICE_ID = "DEVICEID";
    public static final String COL_DEVICE_NUMBER = "NUMBER";
    public static final String COL_DEVICE_LOCATION = "LOCATION";
    public static final String COL_DEVICE_ADMACCOUNT = "ADMACCOUNT";
    public static final String COL_DEVICE_ADDTIME = "ADDTIME";
    public static final String COL_DEVICE_ISPUBLISH = "ISPUBLISH";
    public static final String COL_DEVICE_NAME = "DEVICENAME";

    public static String getCreatUSERSQL(){
        String sql = "CREATE TABLE "
                + TBL_NAME_USER +"(" + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_USER_ACCOUNT + " TEXT,"
                + COL_USER_PASSWORD + " TEXT,"
                + COL_USER_ROLE + " INTEGER,"
                + COL_USER_NAME + " TEXT"
                +")";
        return sql;
    }

    public static String getCreatDEVICESQL(){
        String sql = "CREATE TABLE "
                + TBL_NAME_DEVICE +"(" + COL_DEVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_DEVICE_NUMBER + " TEXT,"
                + COL_DEVICE_LOCATION + " TEXT,"
                + COL_DEVICE_ADMACCOUNT + " TEXT,"
                + COL_DEVICE_NAME + " TEXT,"
                + COL_DEVICE_ISPUBLISH + " INTEGER,"
                + COL_DEVICE_ADDTIME + " INTEGER"
                +")";
        return sql;
    }
}

