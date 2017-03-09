package com.xmy.meterialtest.utils;

import android.util.Log;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.utils
 * @className: LogUtil
 * @author:xiamingyan
 * @time: 2017/3/8	11:11
 * @E-mail：xmydeveloper@163.com
 * @desc: 日志打印工具
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/8
 * @upDateDesc: TODO
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int level = VERBOSE;

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }

    }

    public static void i(String tag, String msg) {
        if (level <= DEBUG) {
            Log.i(tag, msg);
        }

    }

    public static void w(String tag, String msg) {
        if (level <= DEBUG) {
            Log.w(tag, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (level <= DEBUG) {
            Log.e(tag, msg);
        }

    }


}
