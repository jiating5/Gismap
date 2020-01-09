package com.jt.chatmodule.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/2.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class TabLayoutVPAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    List<String> stringList;

    public TabLayoutVPAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, List<String> stringList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//    }
}
