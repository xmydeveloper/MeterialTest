package com.xmy.meterialtest;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest
 * @className: Fruit
 * @author:xiamingyan
 * @time: 2017/2/14	16:58
 * @E-mail：xmydeveloper@163.com
 * @desc: TODO
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/14
 * @upDateDesc: TODO
 */
public class Fruit {
    private String mName;
    private int mImageId;

    public Fruit(String name, int imageId) {
        this.mName = name;
        this.mImageId = imageId;
    }

    public String getName() {
        return mName;
    }

    public int getImageId() {
        return mImageId;
    }

}
