package com.xmy.meterialtest.utils;

import com.xmy.meterialtest.listener.HttpCallBackListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.utils
 * @className: HttpUtils
 * @author:xiamingyan
 * @time: 2017/3/6	15:15
 * @E-mail：xmydeveloper@163.com
 * @desc: 网络请求封装类
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/6
 * @upDateDesc: TODO
 */
public class HttpUtils {

    /**
     * 请求方式一
     *
     * @param address
     * @param listener
     */
    public static void sendHttpRequest(final String address, final HttpCallBackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                    StringBuilder respones = new StringBuilder();
                    String line;
                    while ((line = buffer.readLine()) != null) {
                        respones.append(line);
                    }


                    if (listener != null) {
                        listener.onFinish(respones.toString());
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    listener.onError(e);

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }


            }
        }).start();


    }


    /**
     * 请求方式二
     */
    public static void sendOkhttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


}
