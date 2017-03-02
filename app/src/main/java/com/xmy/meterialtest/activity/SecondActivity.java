package com.xmy.meterialtest.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xmy.meterialtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest.activity
 * @className: SecondActivity
 * @author:xiamingyan
 * @time: 2017/2/28	11:41
 * @E-mail：xmydeveloper@163.com
 * @desc: ContentProvider添加联系人列表
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/28
 * @upDateDesc: TODO
 */
public class SecondActivity extends BaseActivity {

    private ListView mlvContacts;
    private List<String> contactList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private SwipeRefreshLayout mUpdate;


    public static void startSecondActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mlvContacts = (ListView) findViewById(R.id.lv_contacts);
        mUpdate = (SwipeRefreshLayout) findViewById(R.id.sr_lv);
        mUpdate.setColorSchemeResources(R.color.colorPrimary);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        mlvContacts.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }

        //刷新操作
        mUpdate.setOnRefreshListener(mListener);

        mlvContacts.setOnItemClickListener(mOnItemClickListener);


    }

    private SwipeRefreshLayout.OnRefreshListener mListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshContacts();
        }
    };

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (ActivityCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 2);
            } else {
                Uri uri = Uri.parse("tel:" + contactList.get(position));
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            }
        }
    };


    private void refreshContacts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        readContacts();
                        adapter.notifyDataSetChanged();
                        mUpdate.setRefreshing(false);
                    }
                });


            }
        }).start();
    }

    private void readContacts() {

        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    String displyName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactList.add(displyName + "\n" + number);
                    Log.e("SecondActivity", displyName + "--------------" + number);

                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "You  denied the perssion ", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Uri uri = Uri.parse("tel:" + number);
//                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
//                    startActivity(intent);
                } else {
                    Toast.makeText(this, "You  denied the perssion ", Toast.LENGTH_SHORT).show();
                }

                break;
            default:

        }

    }
}
