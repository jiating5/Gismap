package com.jt.chatmodule.view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jt.chatmodule.R;
import com.jt.chatmodule.view.adapter.RecyclerAdapter;
import com.jt.chatmodule.view.bean.LinkManBean;
import com.jt.commonmodule.arouter.ARouterConfig;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterConfig.CHATMODULE_PHONEMAN)
public class PhoneManMainActivity extends AppCompatActivity {

    ListView listView;
    RecyclerView recyclerView;
    List<LinkManBean> linkManBeans = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    RecyclerAdapter adapter;
    ArrayAdapter<String> titleadapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_man_main);
        String[] strings = {
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_CONTACTS"};
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(strings,2);
        }
        //初始化控件
        initViews();
        //获取联系人
        initData();
    }

    private void initData() {
        //获取联系人的URI
        Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();
        Cursor phonebook_label = contentResolver.query(contentUri, null, null, null, "phonebook_label");
        String s="";
        while (phonebook_label.moveToNext()){
            String name = phonebook_label.getString(phonebook_label.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = phonebook_label.getString(phonebook_label.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String phonebook_label1 = phonebook_label.getString(phonebook_label.getColumnIndex("phonebook_label"));
            LinkManBean bean = new LinkManBean(name, number, phonebook_label1, 1);
            if(s.equals(phonebook_label1)){
                linkManBeans.add(bean);
            }else {
                //加数据
                linkManBeans.add(new LinkManBean(null,null,phonebook_label1,0));
                linkManBeans.add(bean);
                s = phonebook_label1;
            }
        }
        adapter.notifyDataSetChanged();
        //字母联动
        initList();
    }

    private void initList() {
        //联动添加的信息
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i <str.length(); i ++){
            titleList.add(str.charAt(i)+"");
        }
        titleadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, titleList);
        listView.setAdapter(titleadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String count = titleList.get(position);
                for (int i=0;i<=linkManBeans.size()-1;i++){
                    //与头字母做比较
                    if(count.equals(linkManBeans.get(i).getPhonebook_label())){
                        //变换位置
                        recyclerView.scrollToPosition(i);
                    }
                }
            }
        });
        //更新适配器
        titleadapter.notifyDataSetChanged();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.phone_rv);
        listView = findViewById(R.id.phone_lv);
        adapter = new RecyclerAdapter(this,linkManBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
