package com.example.demo.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.demo.R;
import com.example.demo.bean.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class MyAdapter extends BaseItemDraggableAdapter<Item, BaseViewHolder> {
    public MyAdapter(int layoutResId, List<Item> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        if (item.getColor().equals("")){
            return;
        }
        helper.setBackgroundColor(R.id.color, Color.parseColor(item.getColor()));
        helper.setText(R.id.name,item.getName());
        if (new File(item.getImg()).exists()){
            helper.setImageBitmap(R.id.img, getLoacalBitmap(item.getImg()));
        }

    }

    private static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}