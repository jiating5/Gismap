package com.jt.chatmodule.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.baweigame.xmpplibrary.XmppManager;
import com.jt.basemodule.base.BaseBeanEntity;
import com.jt.basemodule.base.BaseObservable;
import com.jt.basemodule.base.BaseObserver;
import com.jt.basemodule.net.RetrofitUtils;
import com.jt.chatmodule.R;
import com.jt.chatmodule.api.ApiChat;
import com.jt.chatmodule.view.adapter.FriendRecyclerAdapter;
import com.jt.chatmodule.view.bean.FriendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFriendFragment extends Fragment {

    String usercode;
    ListView listView;
    RecyclerView recyclerView;
    List<FriendBean> friendBeans = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    FriendRecyclerAdapter adapter;
    ArrayAdapter<String> titleadapter;
    RetrofitUtils retrofitUtils = RetrofitUtils.getInstance();
    BaseObservable observable = new BaseObservable();
    ApiChat apiChat = retrofitUtils.create(ApiChat.class);

    public GoodFriendFragment( String usercode) {
        this.usercode = usercode;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_good_friend, container, false);
        //动态权限
        requestPermissions(new String[]{
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_CONTACTS"
        },1);
        //初始化控件
        initViews(view);
        //获取联系人
        initData();
        return view;
    }

    private void initData() {
        //获取好友
        observable.doObservable(apiChat.friendData(usercode),new BaseObserver<BaseBeanEntity<List<FriendBean>>>(){
            @Override
            public void onNext(BaseBeanEntity<List<FriendBean>> listBaseBeanEntity) {
                super.onNext(listBaseBeanEntity);
                String msg = listBaseBeanEntity.getMsg();
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                List<FriendBean> data = listBaseBeanEntity.getData();
                for (int i = 0; i < data.size(); i++) {
                    friendBeans.add(data.get(i));
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
        Log.e("jiating",friendBeans.toString());
        adapter.notifyDataSetChanged();
        initList();
    }

    private void initList() {
        //联动添加的信息
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i <str.length(); i ++){
            titleList.add(str.charAt(i)+"");
        }
        titleadapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, titleList);
        listView.setAdapter(titleadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String count = titleList.get(position);
                for (int i=0;i<=friendBeans.size()-1;i++){
                    //与头字母做比较
//                    if(count.equals(friendBeans.get(i).getPhonebook_label())){
//                        //变换位置
//                        recyclerView.scrollToPosition(i);
//                    }
                }
            }
        });
        //更新适配器
        titleadapter.notifyDataSetChanged();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.gf_rv);
        listView = view.findViewById(R.id.gf_lv);
        adapter = new FriendRecyclerAdapter(getContext(),friendBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

}
