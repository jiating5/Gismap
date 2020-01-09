package com.jt.chatmodule.view.addfragment;


import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jt.basemodule.base.BaseBeanEntity;
import com.jt.basemodule.base.BaseObservable;
import com.jt.basemodule.base.BaseObserver;
import com.jt.basemodule.net.RetrofitUtils;
import com.jt.chatmodule.R;
import com.jt.chatmodule.api.ApiChat;
import com.jt.chatmodule.view.adapter.AddFriendRecyclerAdapter;
import com.jt.chatmodule.view.adapter.FriendRecyclerAdapter;
import com.jt.chatmodule.view.bean.FriendBean;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    String usercode;
    TextView textView,tv_faceadd;
    SearchView searchView;
    RecyclerView recyclerView;
    AddFriendRecyclerAdapter adapter;
    List<FriendBean> list = new ArrayList<>();
    RetrofitUtils retrofitUtils = RetrofitUtils.getInstance();
    BaseObservable observable = new BaseObservable();
    ApiChat apiChat = retrofitUtils.create(ApiChat.class);

    public PeopleFragment(String usercode) {
        this.usercode = usercode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        //6.0动态申请
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.INTERNET,Manifest.permission.CAMERA},101);
        }
        initView(view);
        return view;
    }

    private void initSearchData(final String s) {
        observable.doObservable(apiChat.searchfriendData(s,""),new BaseObserver<BaseBeanEntity<List<FriendBean>>>(){
            @Override
            public void onNext(BaseBeanEntity<List<FriendBean>> listBaseBeanEntity) {
                super.onNext(listBaseBeanEntity);
                String msg = listBaseBeanEntity.getMsg();
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                list.addAll(listBaseBeanEntity.getData());
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e("jia",e.getMessage());
            }
        });
    }

    private void initView(View view) {
        tv_faceadd = view.findViewById(R.id.add_facefriend);
        textView = view.findViewById(R.id.add_phonelinkman);
        searchView = view.findViewById(R.id.addp_search);
        recyclerView = view.findViewById(R.id.add_recycler);
        adapter = new AddFriendRecyclerAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterConfig.CHATMODULE_PHONEMAN).navigation();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                initSearchData(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //添加
        adapter.setListener(new AddFriendRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                initAddData(list.get(position).getUsercode());
            }
        });

        //扫一扫
        tv_faceadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    /**
     *  获取二维码内容
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    //添加好友
                    initAddData(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //添加好友
    private void initAddData(final String friendcode) {
        observable.doObservable(apiChat.addfriendData(usercode,friendcode),new BaseObserver<BaseBeanEntity>(){
            @Override
            public void onNext(BaseBeanEntity listBaseBeanEntity) {
                super.onNext(listBaseBeanEntity);
                String msg = listBaseBeanEntity.getMsg();
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),usercode+friendcode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
