package com.demo.list.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 * ViewPager SimpleFragment的Adapter
 */
public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragmentList = new ArrayList<>();
    public SimpleFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList ){
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
