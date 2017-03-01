package com.xmy.meterialtest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeAll();
    }
}
