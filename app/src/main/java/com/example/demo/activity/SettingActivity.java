package com.example.demo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.demo.R;
import com.example.demo.adapter.MyAdapter;
import com.example.demo.bean.Item;
import com.example.demo.util.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private Button add;
    private MyAdapter adapter;
    private List<Item> itemList;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase writableDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initUI();
    }

    private void initUI() {

        myDatabaseHelper = new MyDatabaseHelper(this, "data.db", null, 1);
        writableDatabase = myDatabaseHelper.getWritableDatabase();
        recyclerView = findViewById(R.id.rev);
        manager = new LinearLayoutManager(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SettingActivity.this, AddActivity.class), 6324);
            }
        });
        recyclerView.setLayoutManager(manager);
        itemList = new ArrayList<>();
        adapter = new MyAdapter(R.layout.bg_item, itemList);
        fillData();
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                deleteToDB(position);
                return false;
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void deleteToDB(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("提示");
        builder.setMessage("确认删除");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String where = "sezhi = ?";
                String[] whereValue = {itemList.get(pos).getColor()};
                itemList.remove(pos);
                writableDatabase.delete("kouhong", where, whereValue);
                Toast.makeText(SettingActivity.this, "确定删除", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void fillData() {
        itemList.clear();
        query(writableDatabase);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 6324:
                fillData();
                break;
        }
    }

    private void query(SQLiteDatabase db) {
        Cursor cursor = db.query("kouhong", null, null, null, null, null, null);
        while (cursor.moveToNext()) {

            String sehao;
            String sezhi;
            String path;
            try {
                sehao = cursor.getString(1);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            try {
                sezhi = cursor.getString(2);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            try {
                path = cursor.getString(3);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Item item = new Item(sehao, sezhi, path);
            itemList.add(item);
        }
        cursor.close();

    }
}
