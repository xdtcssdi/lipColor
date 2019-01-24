package com.example.demo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.util.ColorUtil;
import com.example.demo.util.MyDatabaseHelper;

public class ProcessActivity extends AppCompatActivity {
    private static final String TAG = "ProcessActivity";
    private byte[] data;
    private ImageView img, bg;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase writableDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        img = findViewById(R.id.img);
        bg = findViewById(R.id.bg);
        data = getIntent().getByteArrayExtra("data");
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant == null) {
                    Toast.makeText(ProcessActivity.this, "提取颜色失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                bg.setBackgroundColor(vibrant.getRgb());

                getHsv(vibrant.getRgb());
                //Toast.makeText(ProcessActivity.this, "提取到颜色： " + vibrant.getRgb(), Toast.LENGTH_SHORT).show();
                //vibrant.getHsl();
            }
        };

        Palette.generateAsync(bitmap, listener);
        img.setImageBitmap(bitmap);
    }

    public double getHsv(int color) {
        myDatabaseHelper = new MyDatabaseHelper(this, "data.db", null, 1);
        writableDatabase = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("kouhong", null, null, null, null, null, null);
        double min_score = 1000;
        String name = "默认";
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.move(i);

                String sezhi;
                try {
                    sezhi = cursor.getString(2);
                } catch (Exception e) {
                    continue;
                }
                float hsl1[] = new float[3];
                ColorUtils.colorToHSL(color, hsl1);
                float hsl2[] = new float[3];
                ColorUtils.colorToHSL(Color.parseColor(sezhi), hsl2);
                double dis = ColorUtil.DistanceOf(hsl1, hsl2);

                if (min_score > dis) {
                    min_score = dis;
                    name = cursor.getString(1);
                }
            }
        }
        Toast.makeText(this," 最相似的色号: " + name + " \n 相似度： " + (100-min_score)+"%",Toast.LENGTH_SHORT).show();
        Log.d(TAG, " 最相似的色号: " + name + " \n 相似度： " + (100-min_score)+"%");

        return min_score;
    }

}
