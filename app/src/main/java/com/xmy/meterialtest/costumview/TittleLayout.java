package com.xmy.meterialtest.costumview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xmy.meterialtest.R;
import com.xmy.meterialtest.utils.Utils;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.costumview
 * @className: TittleLayout
 * @author:xiamingyan
 * @time: 2017/2/28	11:45
 * @E-mail：xmydeveloper@163.com
 * @desc: TODO
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/28
 * @upDateDesc: TODO
 */
public class TittleLayout extends LinearLayout {

    public TittleLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tittle, this);
        Button mBack = (Button) findViewById(R.id.tittle_back);
        Button mEditext = (Button) findViewById(R.id.tittle_edit);
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        mEditext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showToast(context, "mEditext is clicked!");
            }
        });

    }


}
