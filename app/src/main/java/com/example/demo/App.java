package com.example.demo;

import android.app.Application;

import java.io.File;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File("/sdcard/kouhong");
        file.mkdir();
    }
}
