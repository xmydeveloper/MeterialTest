package com.xmy.meterialtest.listener;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.listener
 * @className: HttpCallBackListener
 * @author:xiamingyan
 * @time: 2017/3/6	15:17
 * @E-mail：xmydeveloper@163.com
 * @desc: 网络请求回调
 * @upDateAuthor: lenovo
 * @upDate: 2017/3/6
 * @upDateDesc: TODO
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
