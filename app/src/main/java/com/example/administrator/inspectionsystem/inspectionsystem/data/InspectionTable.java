package com.example.administrator.inspectionsystem.inspectionsystem.data;

public class InspectionTable {

    public static final String TBL_NAME_USER = "USER";
    public static final String COL_USER_ID = "USERID";
    public static final String COL_USER_ACCOUNT = "ACCOUNT";
    public static final String COL_USER_PASSWORD= "PASSWORD";
    public static final String COL_USER_ROLE = "ROLE";
    public static final String COL_USER_NAME = "NAME";

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
}

