package com.xiong.hlbindicatordemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/*
 *  @项目名：  HLBIndicatorDemo
 *  @包名：    com.xiong.hlbindicatordemo
 *  @文件名:   MainAdapter
 *  @创建者:   ${huanglibo}
 *  @创建时间: 2016/11/1 23:29
 */

public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    public void updateFragments(ArrayList<Fragment> fragments){
        this.fragments.clear();
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
