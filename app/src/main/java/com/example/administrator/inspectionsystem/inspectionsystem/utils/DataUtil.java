package com.example.administrator.inspectionsystem.inspectionsystem.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.administrator.inspectionsystem.inspectionsystem.bean.Register;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.TableColumn;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.TableName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataUtil<T> {
    public static void add(Object object,SQLiteDatabase db){
        try {
            String tableName = object.getClass().getAnnotation(TableName.class).value();
            Field[] fields = object.getClass().getDeclaredFields();
            ContentValues contentValues = new ContentValues();
            for (Field field:fields){
                String filedName = field.getAnnotation(TableColumn.class).field();
                if (field.getType().equals(String.class)){
                    contentValues.put(filedName,(String)field.get(object));
                }else if (field.getType().equals(int.class) || field.getType().equals(long.class)){
                    contentValues.put(filedName,(int)field.get(object));
                }else {
                    throw new RuntimeException("遇到无法识别的数据类型，请增加定义");
                }
            }
            db.insert(tableName,null,contentValues);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static List getListByExample(Object object ,SQLiteDatabase db){
        try {
            List list = new ArrayList();
            String tableName = object.getClass().getAnnotation(TableName.class).value();
            Field[] fields = object.getClass().getDeclaredFields();
            String selection = "";
            int selectionCount = 0;
            for (Field field : fields){
                field.setAccessible(true);
                if (! isEmpty(field.get(object))){
                    if ( ! "".equals(selection)){
                        selection = selection.concat("AND ");
                    }
                    selection = selection.concat(field.getAnnotation(TableColumn.class).field() +" = ? ");
                    selectionCount++;
                }
            }

            int selectionArgsCount = 0;
            String[] selectionArgs = new String[selectionCount];
            for (Field field : fields){
                if (! isEmpty(field.get(object))){
                    selectionArgs[selectionArgsCount] = field.get(object).toString();
                    selectionArgsCount++;
                }
            }

            Cursor cursor = db.query(tableName,null,selection,selectionArgs,null,null,null);

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Object aim = object.getClass().newInstance();
                for (Field field : fields){
                    TableColumn tableColumn = field.getAnnotation(TableColumn.class);
                    Object value = getCursorValue(cursor,field.getType(),tableColumn.field());
                    field.set(aim,value);
                }
                list.add(aim);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable(SQLiteDatabase db,Class tableClass){
        String tableName = ((TableName)tableClass.getAnnotation(TableName.class)).value();
        Field[] fields = tableClass.getDeclaredFields();
        List<String> fieldList = new ArrayList<>();
        for (Field field : fields){
            TableColumn tableColumn = field.getAnnotation(TableColumn.class);
            String sql = tableColumn.field()
                    + judgeType(field)
                    + (tableColumn.primary() ? " PRIMARY KEY" : "")
                    + (tableColumn.autoIncrease() ? " AUTOINCREMENT" : "");
            fieldList.add(sql);
        }

        String sql = "CREATE TABLE " + tableName +"(";
        for (int i=0 ;i<fieldList.size();i++){
            sql = sql.concat(" "+fieldList.get(i));
            if (i != fieldList.size()-1){
                sql = sql.concat(",");
            }
        }
        sql = sql.concat(")");
        db.execSQL(sql);
    }

    private static String judgeType(Field field){
        if (field.getType().equals(int.class) || field.getType().equals(long.class)){
            return " INTEGER";
        }else if (field.getType().equals(String.class)){
            return " TEXT";
        }else {
            throw new RuntimeException("遇到无法识别的数据类型，请增加定义");
        }
    }

    private static Object getCursorValue(Cursor cursor,Class fieldType,String columnName){
        if (fieldType.equals(int.class) || fieldType.equals(long.class)){
            return cursor.getInt(cursor.getColumnIndex(columnName));
        }else if (fieldType.equals(String.class)){
            return cursor.getString(cursor.getColumnIndex(columnName));
        }else {
            throw new RuntimeException("遇到无法识别的数据类型，请增加定义");
        }
    }

    private static boolean isEmpty(Object value) {
        if (value == null){
            return true;
        }else if (Number.class.isAssignableFrom(value.getClass())){
            return Integer.valueOf(String.valueOf(value)).equals(0);
        }else {
            return false;
        }
    }
}
