package com.jcc.yijing;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HY on 2016/10/25.
 */
public class InitDb {

    public static void insertDB(MyDatabaseHelper dbHelper,String sixYao,String ci) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sixYao", sixYao);
        values.put("ci", ci);
        db.insert("Yi", null, values);
        values.clear();
    }

    public static boolean queryDb(MyDatabaseHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String name ="";
        Cursor cursor = db.query("Yi", null, null, null, null, null,
                null);
        if (cursor.moveToFirst()) {
            do {
                 name = cursor.getString(cursor
                        .getColumnIndex("sixYao"));
              if (name.equals("")){
                  cursor.close();
                  return false;
              }else {
                  cursor.close();
                  return true;
              }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public static String getBo(){
        //得到随机数
        java.util.Random r=new java.util.Random();
        int a = Math.abs(r.nextInt());
        String aS = String.valueOf(a);
        int asb = Integer.parseInt(aS.substring(aS.length()-1,aS.length()));
        String bo = String.valueOf(asb % 2);

        return bo;
    }

    public static String get_guaCi(MyDatabaseHelper dbHelper,String sixBo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String ci ="";
        Cursor cursor = db.query("Yi", null, "sixYao = '"+sixBo+"'", null, null, null,
                null);
        if (cursor.moveToFirst()) {
            do {
                ci = cursor.getString(cursor
                        .getColumnIndex("ci"));
                cursor.close();
                return ci;

            } while (cursor.moveToNext());
        }
        cursor.close();
        return ci;
    }
}

