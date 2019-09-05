package com.passon.aacproject.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.passon.aacproject.R;
import com.passon.aacproject.base.BaseActivity;
import com.passon.aacproject.base.LazyBaseFragment;
import com.passon.aacproject.base.SimpleFragmentPagerAdapter;
import com.passon.aacproject.module.account.AccountFragment;
import com.passon.aacproject.module.discovery.DiscoveryFragment;
import com.passon.aacproject.module.home.HomeFragment;
import com.passon.aacproject.module.investment.InvestFragment;
import com.passon.aacproject.widgets.NoScrollViewPager;
import com.passon.aacproject.widgets.bottomtab.BottomTabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_main)
    NoScrollViewPager mVpMain;
    @BindView(R.id.tab_main)
    BottomTabLayout mTabMain;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Fragment[] tabs = new Fragment[4];
        tabs[0] = HomeFragment.newInstance();
        tabs[1] = InvestFragment.newInstance();
        tabs[2] = DiscoveryFragment.newInstance();
        tabs[3] = AccountFragment.newInstance();
        mVpMain.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(), tabs));
        mVpMain.setOffscreenPageLimit(3);
        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((LazyBaseFragment) tabs[position]).loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabMain.setLocalData(4);
        mTabMain.setUp(mVpMain);
        mTabMain.setCurrentTab(0);
    }

}
