package com.xmy.meterialtest.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.Utils
 * @className: Utils
 * @author:xiamingyan
 * @time: 2017/2/16	17:22
 * @E-mail：xmydeveloper@163.com
 * @desc: 工具类
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/16
 * @upDateDesc: TODO
 */
public class Utils {

    private static Toast toast;

    public static void showToast(Context context, String str) {
        if (toast == null) {

            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();

    }
}
