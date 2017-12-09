package com.quickvideo.quickvideo.mine.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/12/8.
 */

public class MySqlite extends SQLiteOpenHelper {
    public MySqlite(Context context) {
        super(context, "powersen", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创表
        db.execSQL("create table data(_id integer primary key autoincrement,name text,pic text,tag int)");

        db.execSQL("create table shuju(_id integer primary key autoincrement,name text,pic text,tag int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
