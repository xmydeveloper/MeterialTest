package com.xmy.meterialtest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xmy.meterialtest.R;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest
 * @className: FruitActivity
 * @author:xiamingyan
 * @time: 2017/2/15	10:56
 * @E-mail：xmydeveloper@163.com
 * @desc: TODO
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/15
 * @upDateDesc: TODO
 */
public class FruitActivity extends BaseActivity {

    private static final String FRUIT_NAME = "fruit_name";
    private static final String FRUIT_IMAGE_ID = "fruit_image_id";

    public static void startFruitActivity(Context mContent, String fruitName, int fruitId) {
        Intent intent = new Intent();
        intent.setClass(mContent, FruitActivity.class);
        intent.putExtra(FRUIT_NAME, fruitName);
        intent.putExtra(FRUIT_IMAGE_ID + "", fruitId);
        mContent.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        TextView fruitContentText = (TextView) findViewById(R.id.fruit_content_text);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        FloatingActionButton mFruitFla = (FloatingActionButton) findViewById(R.id.fruit_fla);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
        }

        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);

        mFruitFla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "评论被点击了", Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FruitActivity.this, "取消动作", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });


    }

    @NonNull
    private String generateFruitContent(String fruitName) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            buffer.append(fruitName);
        }
        return buffer.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
