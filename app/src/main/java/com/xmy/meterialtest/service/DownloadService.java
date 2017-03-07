package com.xmy.meterialtest.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.xmy.meterialtest.R;
import com.xmy.meterialtest.activity.MainActivity;
import com.xmy.meterialtest.listener.DownloadListener;
import com.xmy.meterialtest.task.DownloadTask;

import java.io.File;


/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.service
 * @className: DownloadService
 * @author:xiamingyan
 * @time: 2017/3/7	15:38
 * @E-mail：xmydeveloper@163.com
 * @desc: 后台下载服务
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/7
 * @upDateDesc: TODO
 */
public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {

            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success!", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFaild() {

        }

        @Override
        public void onPause() {
            Toast.makeText(DownloadService.this, "Pause", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancle() {
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Cancled", Toast.LENGTH_SHORT).show();
        }
    };


    private Notification getNotification(String tittle, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(tittle);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);

        }

        return builder.build();
    }


    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    public class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }


        public void cancleDowload() {
            if (downloadTask != null) {
                downloadTask.cancleDownload();
            } else {
                if (downloadUrl != null) {
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directoryName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directoryName + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }


    private DownloadBinder mBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
