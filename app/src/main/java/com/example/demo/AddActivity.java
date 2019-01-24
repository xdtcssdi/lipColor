package com.example.demo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.util.MyDatabaseHelper;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHOOSE = 23;
    List<Uri> mSelected;
    private EditText sehao;
    private EditText sezhi;
    private Button add, cancel;
    private ImageView bg;
    private boolean flag;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase writableDatabase;
    private String path;

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{
                    MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDatabaseHelper = new MyDatabaseHelper(this, "data.db", null, 1);
        writableDatabase = myDatabaseHelper.getWritableDatabase();
        sehao = findViewById(R.id.sehao);
        sezhi = findViewById(R.id.sezhi);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sehao_ = sehao.getText().toString();
                String sezhi_ = sezhi.getText().toString();
                if ("".equals(sehao_) || "".equals(sezhi_) || !flag) {
                    Toast.makeText(AddActivity.this, "不可为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Color.parseColor(sehao_);
                    Toast.makeText(AddActivity.this, "不可输入色值", Toast.LENGTH_SHORT).show();
                    return;
                }catch (Exception e){

                }

                try {
                    Color.parseColor(sezhi_);
                } catch (Exception e) {
                    Toast.makeText(AddActivity.this, "色值错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                insert(writableDatabase, sehao_, sezhi_, path);
            }
        });
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bg = findViewById(R.id.bg);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.from(AddActivity.this)
                        .choose(MimeType.ofAll(), false) // 选择 mime 的类型
                        .countable(false)
                        .maxSelectable(1) // 图片选择的最多数量
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                        .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            path = getRealFilePath(this, Matisse.obtainResult(data).get(0));
            Log.d("Matisse", "mSelected: " + path);
            bg.setImageURI(Matisse.obtainResult(data).get(0));
            flag = true;
        }
    }

    private void insert(SQLiteDatabase db, String sehao, String sezhi, String path) {

        if (query(db, sezhi)) {
            Toast.makeText(this, "当前输入的色值已经存在", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues cValue = new ContentValues();
        cValue.put("sezhi", sezhi);
        cValue.put("sehao", sehao);
        cValue.put("path", path);
        db.insert("kouhong", null, cValue);
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean query(SQLiteDatabase db, String sezhi_) {
        Cursor cursor = db.query("kouhong", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.move(i);

                String sezhi = cursor.getString(2);
                if (sezhi.equals(sezhi_)) {
                    return true;
                }
            }
        }
        return false;
    }

}
