package com.college17summer.android.fleeting.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.college17summer.android.fleeting.R;
import com.college17summer.android.fleeting.views.fragments.RecommendFragment;
import com.college17summer.android.fleeting.views.fragments.SettingFragment;
import com.college17summer.android.fleeting.views.fragments.SortedFragment;
import com.college17summer.android.fleeting.views.fragments.SortedVideosFragment;
import com.facebook.drawee.backends.pipeline.Fresco;


import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private final String TAG = "Main";
    private RecommendFragment recommendFragment;
    private SortedFragment sortedFragment;
    private SettingFragment settingFragment;


    BottomNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        bottomNav = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar_container);

        bottomNav
                .addItem(new BottomNavigationItem(R.drawable.bottomnav_recommend, "推荐")).setActiveColor(R.color.red)
                .addItem(new BottomNavigationItem(R.drawable.bottomnav_sorted, "分类")).setActiveColor(R.color.red)
                .addItem(new BottomNavigationItem(R.drawable.bottomnav_setting, "设置")).setActiveColor(R.color.red)
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNav.setTabSelectedListener(this);
        Log.e(TAG, "init: Success");

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        recommendFragment = RecommendFragment.newInstance("user1");
        transaction.add(R.id.frame_main_window, recommendFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (this.recommendFragment == null) {
                    this.recommendFragment = RecommendFragment.newInstance("user1");
                }
                transaction.replace(R.id.frame_main_window, recommendFragment);
                Log.e(TAG, "Recommend Fragment: Success");
                break;
            case 1:
                // TODO: Add sorted videos window
                if (this.sortedFragment == null) {
                    this.sortedFragment = SortedFragment.newInstance("kind1");
                }
                transaction.replace(R.id.frame_main_window, sortedFragment);
                break;
            case 2:
                // TODO: Add sorted videos window
                if (this.settingFragment == null) {
                    this.settingFragment = SettingFragment.newInstance("userId");
                }
                transaction.replace(R.id.frame_main_window, settingFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}

