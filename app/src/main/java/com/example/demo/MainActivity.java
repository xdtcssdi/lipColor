package com.example.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button button_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
			callCamera();
		}
	}

	private void initUI() {
		button_register = (Button) this.findViewById(R.id.button_register);
		button_register.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.button_register:
				Intent intent = new Intent(this,VideoRecognise.class);
				startActivity(intent);
				break;
			default:
				break;
		}
	}

	//处理申请权限的结果
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case 1:
				int gsize=grantResults.length;
				int flag=0;
				for(int i=0;i<gsize;i++)
				{
					if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
						flag=1;
					}
				}
				if (flag==0) {
					Toast.makeText(MainActivity.this, "申请权限成功", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "you refused the camera function", Toast.LENGTH_SHORT).show();
					this.finish();
				}
				break;
		}
	}

	public void callCamera() {
		String callPhone = Manifest.permission.CAMERA;
		String writestorage = Manifest.permission.WRITE_EXTERNAL_STORAGE;;
		String[] permissions = new String[]{callPhone,writestorage};
		int selfPermission = ActivityCompat.checkSelfPermission(this, callPhone);
		int selfwrite = ActivityCompat.checkSelfPermission(this, writestorage);

		if (selfPermission != PackageManager.PERMISSION_GRANTED || selfwrite != PackageManager.PERMISSION_GRANTED ) {
			ActivityCompat.requestPermissions(this, permissions, 1);
		}
	}
}
