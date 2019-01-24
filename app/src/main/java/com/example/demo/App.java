package com.example.demo;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo.util.MyDatabaseHelper;

import java.io.File;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File("/sdcard/kouhong");
        file.mkdir();

        init();
    }

    public void init() {

        MyDatabaseHelper myDatabaseHelper;
        SQLiteDatabase writableDatabase;

        myDatabaseHelper = new MyDatabaseHelper(this, "data.db", null, 1);
        writableDatabase = myDatabaseHelper.getWritableDatabase();

        insert(writableDatabase,"304加州红","#eb4a47");
        insert(writableDatabase,"305缪斯红","#df353b");
        insert(writableDatabase,"306法式红","#c4290a");
        insert(writableDatabase,"325圣水红","#dd3122");
        insert(writableDatabase,"315覆盆子红","#b83252");
        insert(writableDatabase,"317暖柿红","#e24139");
        insert(writableDatabase,"102优雅米色","#e07b71");
        insert(writableDatabase,"103迷人茶色","#d75e5e");
        insert(writableDatabase,"105阳光小麦","#cc585a");
        insert(writableDatabase,"201糖果玫瑰","#df595f");
        insert(writableDatabase,"202幻想玫瑰","#d9454f");
        insert(writableDatabase,"204樱桃玫瑰","#c13248");
        insert(writableDatabase,"205加仑玫瑰","#e83e5c");
        insert(writableDatabase,"209明艳玫瑰","#f24c7c");
        insert(writableDatabase,"210大丽玫瑰","#e96588");
        insert(writableDatabase,"214复古玫瑰","#d1364e");
        insert(writableDatabase,"301西瓜红","#e44765");
        insert(writableDatabase,"302芭比红","#ed5a7d");
        insert(writableDatabase,"303珊瑚红","#eb5f77");
        insert(writableDatabase,"323高雅莓","#da4c79");
        insert(writableDatabase,"324秀场红","#ee4d5c");
        insert(writableDatabase,"326勃艮第红","#b42d3f");
        insert(writableDatabase,"327莓紫红","#c23861");
    }


    private void insert(SQLiteDatabase db, String sehao, String sezhi) {
        if (query(db,sezhi)){
            return;
        }
        String path = "";
        ContentValues cValue = new ContentValues();
        cValue.put("sezhi", sezhi);
        cValue.put("sehao", sehao);
        cValue.put("path", path);
        db.insert("kouhong", null, cValue);
    }

    private boolean query(SQLiteDatabase db, String sezhi_) {

        String sql = "select * from kouhong where sezhi =?";
        String[] args = new String[]{sezhi_};

        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToFirst()) {
            String sezhi = cursor.getString(2);
            if (sezhi.equals(sezhi_)) {
                return true;
            }
        }

        return false;
    }
}
