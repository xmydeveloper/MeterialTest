package com.xmy.meterialtest.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.xmy.meterialtest.R;
import com.xmy.meterialtest.activity.NotificationActivity;

import java.util.Random;

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

    /**
     * 通知
     *
     * @param context
     * @param tittle  标题
     * @param content 内容
     */

    public static void showNotifycation(Context context, String tittle, String content) {

        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent intent1 = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationManager systemService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = new NotificationCompat.Builder(context).setContentTitle(tittle)
//                .setContentText(content)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_backup)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_delete))
//                .setContentIntent(intent1)
//                .setAutoCancel(true)
//                .setVibrate(new long[]{0,1000,1000,1000})
//                .build();


        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(tittle)
                .setContentText(content)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_backup)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_delete))
                .setContentIntent(intent1)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 1000, 1000, 1000})
//                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_delete)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        Random random = new Random();
        int i = random.nextInt();
        systemService.notify(i, notification);
    }


}
