package com.example.administrator.inspectionsystem.inspectionsystem.data;

public class InspectionTable {

    public static final String TBL_NAME_USER = "USER";
    public static final String COL_USER_ID = "USERID";
    public static final String COL_USER_ACCOUNT = "ACCOUNT";
    public static final String COL_USER_PASSWORD= "PASSWORD";
    public static final String COL_USER_ROLE = "ROLE";
    public static final String COL_USER_NAME = "NAME";

    public static final String TBL_NAME_ADVICE = "ADVICE";
    public static final String COL_ADVICE_ID = "ADVICEID";
    public static final String COL_ADVICE_NUMBER = "NUMBER";
    public static final String COL_ADVICE_LOCATION = "LOCATION";
    public static final String COL_ADVICE_ADMACCOUNT = "ADMACCOUNT";
    public static final String COL_ADVICE_ADDTIME = "ADDTIME";
    public static final String COL_ADVICE_ISPUBLISH = "ISPUBLISH";
    public static final String COL_ADVICE_NAME = "ADVICENAME";

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

    public static String getCreatADVICESQL(){
        String sql = "CREATE TABLE "
                + TBL_NAME_ADVICE +"(" + COL_ADVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_ADVICE_NUMBER + " TEXT,"
                + COL_ADVICE_LOCATION + " TEXT,"
                + COL_ADVICE_ADMACCOUNT + " TEXT,"
                + COL_ADVICE_NAME + " TEXT,"
                + COL_ADVICE_ISPUBLISH + " INTEGER,"
                + COL_ADVICE_ADDTIME + " INTEGER"
                +")";
        return sql;
    }
}

