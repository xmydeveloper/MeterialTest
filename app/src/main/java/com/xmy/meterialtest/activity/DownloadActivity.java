package com.xmy.meterialtest.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xmy.meterialtest.R;
import com.xmy.meterialtest.service.DownloadService;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.activity
 * @className: DownloadActivity
 * @author:xiamingyan
 * @time: 2017/3/7	14:07
 * @E-mail：xmydeveloper@163.com
 * @desc: 开启下载界面
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/7
 * @upDateDesc: TODO
 */
public class DownloadActivity extends BaseActivity implements View.OnClickListener {


    public static void startDownloadActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadActivity.class);
        context.startActivity(intent);
    }


    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    private Button mBtnStart;
    private Button mBtnPause;
    private Button mBtnCancel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_download);
        initView();
        initEvent();

    }


    private void initView() {
        mBtnStart = (Button) findViewById(R.id.bt_start);
        mBtnPause = (Button) findViewById(R.id.bt_pause);
        mBtnCancel = (Button) findViewById(R.id.bt_cancel);
        mBtnStart.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    private void initEvent() {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(DownloadActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DownloadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }


    }


    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }


        switch (v.getId()) {
            case R.id.bt_start:
                String url = "http://dlied5.myapp.com/myapp/1104466820/sgame/2017_com.tencent.tmgp.sgame_h100_1.17.1.23.apk";
                downloadBinder.startDownload(url);
                break;
            case R.id.bt_pause:
                downloadBinder.pauseDownload();
                break;
            case R.id.bt_cancel:
                downloadBinder.cancleDowload();
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }


}
