package com.jt.chatmodule.view.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.jt.basemodule.activity.BaseActivity;
import com.jt.basemodule.ui.CustomTitleBar;
import com.jt.chatmodule.R;
import com.jt.chatmodule.view.adapter.TabLayoutVPAdapter;
import com.jt.chatmodule.view.addfragment.GroupFragment;
import com.jt.chatmodule.view.addfragment.PeopleFragment;
import com.jt.commonmodule.arouter.ARouterConfig;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterConfig.CHATMODULE_ADD)
public class AddActivity extends BaseActivity {

    @Autowired
    String usercode;
    TabLayout tabLayout;
    ViewPager viewPager;
    CustomTitleBar customTitleBar;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    TabLayoutVPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ARouter.getInstance().inject(this);
        tabLayout = findViewById(R.id.add_tab);
        viewPager = findViewById(R.id.add_vp);
        customTitleBar = findViewById(R.id.add_ctb);
        customTitleBar.leftOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        fragmentList.add(new PeopleFragment(usercode));
        fragmentList.add(new GroupFragment());
        titleList.add("找人");
        titleList.add("找群");
        adapter = new TabLayoutVPAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
