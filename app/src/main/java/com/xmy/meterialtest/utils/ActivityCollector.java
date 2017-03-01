package com.xmy.meterialtest.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.utils
 * @className: ActivityCollector
 * @author:xiamingyan
 * @time: 2017/3/1	14:26
 * @E-mail：xmydeveloper@163.com
 * @desc: 随时随地退出Activity
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/1
 * @upDateDesc: TODO
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static boolean addActivity(Activity activity) {
        return activities.add(activity);
    }

    public static boolean removeActivity(Activity activity) {
        return activities.remove(activity);
    }

    public static void removeAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }


}
