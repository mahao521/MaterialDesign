package com.mahao.materialdesigndemo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Penghy on 2017/7/14.
 */


public class FragmentAdapter extends FragmentPagerAdapter {


    private List<Fragment> mFragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Bundle arguments = mFragmentList.get(position).getArguments();
        String string = arguments.getString("title");
        return string;

    }
}
