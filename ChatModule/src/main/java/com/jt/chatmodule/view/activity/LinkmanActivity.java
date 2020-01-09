package com.jt.chatmodule.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.jt.basemodule.activity.BaseActivity;
import com.jt.basemodule.ui.CustomTitleBar;
import com.jt.chatmodule.R;
import com.jt.chatmodule.view.adapter.TabLayoutVPAdapter;
import com.jt.chatmodule.view.fragment.GoodFriendFragment;
import com.jt.chatmodule.view.fragment.GroupChatFragment;
import com.jt.chatmodule.view.fragment.GroupFragment;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.commonmodule.sp.spUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 贾婷
 * @date 2019/12/28.
 * GitHub：https://github.com/jiating5
 * description：联系人
 */

@Route(path = ARouterConfig.CHATMODULE_LINKMAN)
public class LinkmanActivity extends BaseActivity {

    @Autowired
    String usercode;
    TabLayout tabLayout;
    ViewPager viewPager;
    CustomTitleBar customTitleBar;
    TabLayoutVPAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkman);
        ARouter.getInstance().inject(this);
        Toast.makeText(this,usercode,Toast.LENGTH_SHORT).show();
        initView();
        initdata();
        spUtils spUtils = new spUtils(this);
        Map<String, String> map = spUtils.ReadSp();
        String name = map.get("name");
        String password = map.get("password");
        Toast.makeText(this,name+password,Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        tabLayout = findViewById(R.id.link_tl);
        viewPager = findViewById(R.id.link_vp);
        customTitleBar = findViewById(R.id.link_ctb);
        customTitleBar.rightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterConfig.CHATMODULE_ADD).withString("usercode",usercode).navigation();
            }
        });
    }

    private void initdata() {
        fragments.add(new GoodFriendFragment(usercode));
        fragments.add(new GroupFragment());
        fragments.add(new GroupChatFragment());
        titles.add("好友");
        titles.add("分组");
        titles.add("群聊");
        adapter = new TabLayoutVPAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
