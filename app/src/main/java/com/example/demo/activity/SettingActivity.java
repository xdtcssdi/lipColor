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
        adapter = new MyAdapter(this,itemList);
        fillData();
        recyclerView.setAdapter(adapter);
    }


    private void fillData() {
        itemList.clear();
        query(writableDatabase);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fillData();
    }

    private void query(SQLiteDatabase db) {
        Cursor cursor = db.query("kouhong", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String sehao;
            String sezhi;
            String path;
            sehao = cursor.getString(1);
            sezhi = cursor.getString(2);
            path = cursor.getString(3);

            Item item = new Item(sehao, sezhi, path);
            itemList.add(item);
        }
        cursor.close();

    }
}
