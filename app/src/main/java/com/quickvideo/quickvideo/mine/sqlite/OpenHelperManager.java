package com.quickvideo.quickvideo.mine.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.quickvideo.quickvideo.mine.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/8.
 */

public class OpenHelperManager {

    private final SQLiteDatabase db;
    private String table = "data";

    public OpenHelperManager(Context context) {
        MySqlite sqlite = new MySqlite(context);
        db = sqlite.getWritableDatabase();
    }

    public void deleteData(String table) {
        db.execSQL("delete from " + table);
    }

    //存储数据
    public void putData(Bean bean) {
        ContentValues values = new ContentValues();
        values.put("name", bean.getName());
        values.put("pic", bean.getPic());
        values.put("tag", bean.getTag());

        db.insert(table, null, values);
    }

    //存储数据
    public void putShuju(Bean bean) {
        ContentValues values = new ContentValues();
        values.put("name", bean.getName());
        values.put("pic", bean.getPic());
        values.put("tag", bean.getTag());

        db.insert("shuju", null, values);
    }


    //通过cursor进行查找数据，判断表中数据是否与要添加的数据一致，一致则不添加
    public boolean ifEquals(Bean bean) {
        Cursor cursor2 = db.query(table, null, null, null, null, null, null);
        while (cursor2.moveToNext()) {
            if (cursor2.getString(cursor2.getColumnIndex("name")).equals(bean.getName())
                    && cursor2.getString(cursor2.getColumnIndex("pic")).equals(bean.getPic())
                    && cursor2.getInt(cursor2.getColumnIndex("tag")) == bean.getTag()) {
                return true;
            }
        }
        return false;
    }


    //通过cursor进行查找数据，判断表中数据是否与要添加的数据一致，一致则不添加
    public boolean ifEqualsTwo(Bean bean) {
        Cursor cursor2 = db.query("shuju", null, null, null, null, null, null);
        while (cursor2.moveToNext()) {
            if (cursor2.getString(cursor2.getColumnIndex("name")).equals(bean.getName())
                    && cursor2.getString(cursor2.getColumnIndex("pic")).equals(bean.getPic())
                    && cursor2.getInt(cursor2.getColumnIndex("tag")) == bean.getTag()) {
                return true;
            }
        }
        return false;
    }

    // 查询数据库；
    public List<Bean> queryData() {
        List<Bean> list = new ArrayList<>();
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Bean bean1 = new Bean();
            // 自增ID，用来修改数据；
            bean1.tag = cursor.getInt(cursor.getColumnIndex("tag"));
            bean1.name = cursor.getString(cursor.getColumnIndex("name"));
            bean1.pic = cursor.getString(cursor.getColumnIndex("pic"));
            list.add(bean1);

        }
        return list;
    }

    // 查询数据库2；
    public List<Bean> queryShuju() {
        List<Bean> list = new ArrayList<>();
        Cursor cursor = db.query("shuju", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Bean bean1 = new Bean();
            // 自增ID，用来修改数据；
            bean1.tag = cursor.getInt(cursor.getColumnIndex("tag"));
            bean1.name = cursor.getString(cursor.getColumnIndex("name"));
            bean1.pic = cursor.getString(cursor.getColumnIndex("pic"));
            list.add(bean1);

        }
        return list;
    }

}
