package com.sample.androidwear.samplewatchapp;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.GridViewPager;

import com.sample.androidwear.samplewatchapp.adapter.AppGridPagerAdapter;
import com.sample.androidwear.samplewatchapp.preference.SettingsPrefUtils;

public class MainActivity extends WearableActivity {

    private GridViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();
        mPager = (GridViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(new AppGridPagerAdapter(this, getFragmentManager()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPager.setBackgroundColor(SettingsPrefUtils.getColor(this));
    }
}

