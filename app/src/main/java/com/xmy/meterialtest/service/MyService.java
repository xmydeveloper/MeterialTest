package com.xmy.meterialtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.service
 * @className: MyService
 * @author:xiamingyan
 * @time: 2017/3/6	16:47
 * @E-mail：xmydeveloper@163.com
 * @desc: TODO
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/6
 * @upDateDesc: TODO
 */
public class MyService extends Service {

    private DowlaodBinder mBinder = new DowlaodBinder();

    public class DowlaodBinder extends Binder {
        public void startDowload() {
            Log.e("MyService", "start download!");
        }

        public int getProgress() {
            Log.e("MyService", "start download!");
            return 0;
        }

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
