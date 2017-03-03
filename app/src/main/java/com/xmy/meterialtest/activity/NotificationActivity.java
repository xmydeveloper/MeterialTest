package com.xmy.meterialtest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.xmy.meterialtest.R;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.activity
 * @className: NotificationActivity
 * @author:xiamingyan
 * @time: 2017/3/3	15:27
 * @E-mail：xmydeveloper@163.com
 * @desc: 通知页面
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/3
 * @upDateDesc: TODO
 */
public class NotificationActivity extends BaseActivity {
    public static void startNotificationActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_notification);
    }
}
