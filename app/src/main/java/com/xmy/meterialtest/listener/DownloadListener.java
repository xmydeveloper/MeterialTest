package com.xmy.meterialtest.listener;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.listener
 * @className: DownloadListener
 * @author:xiamingyan
 * @time: 2017/3/7	14:10
 * @E-mail：xmydeveloper@163.com
 * @desc: 下载监听接口
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/7
 * @upDateDesc: TODO
 */
public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFaild();

    void onPause();

    void onCancle();
}
