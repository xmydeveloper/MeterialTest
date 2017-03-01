package com.xmy.meterialtest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.xmy.meterialtest.R;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.activity
 * @className: SecondActivity
 * @author:xiamingyan
 * @time: 2017/2/28	11:41
 * @E-mail：xmydeveloper@163.com
 * @desc: TODO
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/28
 * @upDateDesc: TODO
 */
public class SecondActivity extends BaseActivity {

    public static void startSecondActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
