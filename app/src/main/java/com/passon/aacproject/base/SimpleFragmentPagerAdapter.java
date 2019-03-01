package com.passon.aacproject.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by fangmigndong on 2017/1/17.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mTabs;
    private String[] mTabTitles;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Fragment[] tabs, String[] tabtitles) {
        super(fm);
        mTabs = tabs;
        mTabTitles = tabtitles;
    }

    public SimpleFragmentPagerAdapter(FragmentManager fm, Fragment[] tabs) {
        super(fm);
        mTabs = tabs;
        mTabTitles = new String[tabs.length];
    }

    @Override
    public Fragment getItem(int position) {
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

    public void clear() {
        mTabs = new Fragment[0];
        mTabTitles = new String[0];
        notifyDataSetChanged();
    }
}
