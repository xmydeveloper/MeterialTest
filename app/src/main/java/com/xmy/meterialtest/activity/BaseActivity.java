package com.xmy.meterialtest.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.xmy.meterialtest.utils.ActivityCollector;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.activity
 * @className: BaseActivity
 * @author:xiamingyan
 * @time: 2017/3/1	14:40
 * @E-mail：xmydeveloper@163.com
 * @desc: Activity的基类
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/1
 * @upDateDesc: TODO
 */
public class BaseActivity extends AppCompatActivity {

    private ForceOffLineRecever recever;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.xmy.meterialtest.FORCE_OFFLINE");
        recever = new ForceOffLineRecever();
        registerReceiver(recever, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeAllActivity();
        if (recever != null) {
            unregisterReceiver(recever);
            recever = null;
        }
    }

    private class ForceOffLineRecever extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline ,please try to login again");
            builder.setCancelable(false);

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.removeAllActivity();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();

        }
    }
}
