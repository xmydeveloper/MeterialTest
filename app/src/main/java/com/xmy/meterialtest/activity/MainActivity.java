package com.xmy.meterialtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xmy.meterialtest.R;
import com.xmy.meterialtest.adapter.FruitAdapter;
import com.xmy.meterialtest.bean.Fruit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFla;
    private RecyclerView mRecyclerView;

    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.apple),
            new Fruit("Apple", R.drawable.apple),
            new Fruit("Banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange),
            new Fruit("Grape", R.drawable.grape),
            new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Mango", R.drawable.mango)
    };

    private List<Fruit> fruitList = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private FruitAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        try {
            FileOutputStream fileOutputStream = openFileOutput("hehe", MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerview = mNavigationView.getHeaderView(0);

        CircleImageView circleImageView = (CircleImageView) headerview.findViewById(R.id.nav_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO： 从相册选择图片或者拍照
//                HttpUtils.sendOkhttpRequest("https://www.hao123.com", new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                    }
//                });

//                HttpUtils.sendHttpRequest("https://www.hao123.com", new HttpCallBackListener() {
//                    @Override
//                    public void onFinish(String response) {
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });


            }
        });


        mNavigationView.setCheckedItem(R.id.nav_call);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        SecondActivity.startSecondActivity(MainActivity.this);
                        break;
                    case R.id.nav_friends:
                        Intent intent = new Intent();
                        intent.setAction("com.xmy.meterialtest.FORCE_OFFLINE");
                        sendBroadcast(intent);
                        break;
                    case R.id.nav_task:
                        DownloadActivity.startDownloadActivity(MainActivity.this);

                        break;
                    default:

                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });


        mFla = (FloatingActionButton) findViewById(R.id.fla);
        mFla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data Delete", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Data Restored", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });


        initFruit();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);//最后一个参数：true 从末端开始向左滑，false 反之
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        adapter = new FruitAdapter(fruitList);
        mRecyclerView.setAdapter(adapter);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });


    }


//    /**
//     * 服务与进程通讯
//     */
//
//    private ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService.DowlaodBinder binder = (MyService.DowlaodBinder) service;
//            binder.startDowload();
//            int progress = binder.getProgress();
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };


    private void refreshFruits() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initFruit();
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                }
            });


        }).start();

    }

    private void initFruit() {
        fruitList.clear();
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(fruits.length);
            fruitList.add(fruits[nextInt]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.backup:
                Toast.makeText(this, "backup was clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete was clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "setting was clicked!", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
