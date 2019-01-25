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

        insert(writableDatabase,"304加州红","#d94843");
        insert(writableDatabase,"305缪斯红","#e54938");
        insert(writableDatabase,"306法式红","#ba3a2b");
        insert(writableDatabase,"325圣水红","#e14528");
        insert(writableDatabase,"315覆盆子红","#9b363d");
        insert(writableDatabase,"317暖柿红","#de5338");
        insert(writableDatabase,"102优雅米色","#eb806c");
        insert(writableDatabase,"103迷人茶色","#c15e51");
        insert(writableDatabase,"105阳光小麦","#b95655");
        insert(writableDatabase,"201糖果玫瑰","#e26464");
        insert(writableDatabase,"202幻想玫瑰","#e65558");
        insert(writableDatabase,"204樱桃玫瑰","#b34347");
        insert(writableDatabase,"205加仑玫瑰","#ce4e60");
        insert(writableDatabase,"209明艳玫瑰","#e35465");
        insert(writableDatabase,"210大丽玫瑰","#e7677b");
        insert(writableDatabase,"214复古玫瑰","#b64556");
        insert(writableDatabase,"301西瓜红","#e04c4c");
        insert(writableDatabase,"302芭比红","#e15158");
        insert(writableDatabase,"303珊瑚红","#eb5f77");
        insert(writableDatabase,"323高雅莓","#c14654");
        insert(writableDatabase,"324秀场红","#ea4d45");
        insert(writableDatabase,"326勃艮第红","#923542");
        insert(writableDatabase,"327莓紫红","#993e56");
        insert(writableDatabase,"307石榴红","#912e2c");
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

        while (cursor.moveToNext()) {
            String sezhi = cursor.getString(2);
            if (sezhi.equals(sezhi_)) {
                return true;
            }
        }

        return false;
    }
}
