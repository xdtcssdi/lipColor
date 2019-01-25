package com.example.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.EditActivity;
import com.example.demo.activity.SettingActivity;
import com.example.demo.bean.Item;
import com.example.demo.util.MyDatabaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerHolder>  {

    public static Bitmap getLoacalBitmap(String url) {
        if (url==null){
            url = "";
        }
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Context mContext;
    private List<Item> dataList;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase writableDatabase;
    public MyAdapter(Context context,List<Item> items) {
        this.mContext = context;
        this.dataList = items;
        myDatabaseHelper = new MyDatabaseHelper(mContext, "data.db", null, 1);
        writableDatabase = myDatabaseHelper.getWritableDatabase();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bg_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, final int position) {
        Item item = dataList.get(position);
        if (item.getColor().equals("")) {
            return;
        }
        holder.color.setBackgroundColor(Color.parseColor(item.getColor()));
        holder.name.setText(item.getName());
        try {
            if (new File(item.getImg()).exists()) {
                holder.img.setImageBitmap(getLoacalBitmap(item.getImg()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditActivity.class);
                Item item = dataList.get(position);
                intent.putExtra("color", item.getColor());
                intent.putExtra("img", item.getImg());
                intent.putExtra("name", item.getName());
                ((Activity)mContext).startActivityForResult(intent, 6325);
            }
        });


        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("提示");
                builder.setMessage("确认删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String where = "sezhi = ?";
                        String[] whereValue = {dataList.get(position).getColor()};
                        dataList.remove(position);
                        writableDatabase.delete("kouhong", where, whereValue);
                        Toast.makeText(mContext, "确定删除", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img,color;
        View view;
        private RecyclerHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            color = itemView.findViewById(R.id.color);
            view = itemView;
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}