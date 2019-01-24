package com.example.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.support.v7.graphics.Palette;
import android.widget.Toast;

public class ProcessActivity extends AppCompatActivity {
    private byte[] data;
    private ImageView img,bg;

    private static final String TAG = "ProcessActivity";
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
                if (vibrant==null){
                    Toast.makeText(ProcessActivity.this,"提取颜色失败",Toast.LENGTH_SHORT).show();
                    return;
                }
                bg.setBackgroundColor(vibrant.getRgb());
                Toast.makeText(ProcessActivity.this,"提取到颜色： "+ vibrant.getRgb(),Toast.LENGTH_SHORT).show();
            vibrant.getHsl();
            }
        };

        Palette.generateAsync(bitmap,listener);


        img.setImageBitmap(bitmap);
    }


}
