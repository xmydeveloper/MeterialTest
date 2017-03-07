package com.xmy.meterialtest.task;

import android.os.AsyncTask;
import android.os.Environment;

import com.xmy.meterialtest.listener.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.task
 * @className: DownloadTask
 * @author:xiamingyan
 * @time: 2017/3/7	14:15
 * @E-mail：xmydeveloper@163.com
 * @desc: 异步线程处理下载
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/7
 * @upDateDesc: TODO
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;
    private final DownloadListener listener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;


    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }


    @Override
    protected Integer doInBackground(String... params) {
        File file = null;
        InputStream in = null;
        RandomAccessFile saveFile = null;

        try {
            long downloadLength = 0;//记录下载文件的长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);

            if (file.exists()) {
                downloadLength = file.length();
            }


            long contentLength = getContentLength(downloadUrl);

            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                //已经下载的字节和文件总字节相等，说明下载完成
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "byte" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();

            Response response = client.newCall(request).execute();
            if (response != null) {
                in = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downloadLength);//跳过已经下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = in.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        saveFile.write(b, 0, len);
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);

                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (saveFile != null) {
                    saveFile.close();
                }

                if (isCanceled && file != null) {
                    file.delete();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();

        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();

            return contentLength;
        }
        return 0;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFaild();
                break;
            case TYPE_PAUSED:
                listener.onPause();
                break;
            case TYPE_CANCELED:
                listener.onCancle();
                break;
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancleDownload() {
        isCanceled = true;
    }


}
