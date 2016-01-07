package com.sample.androidwear.samplewatchapp.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.View;
import android.widget.Toast;

import com.sample.androidwear.samplewatchapp.R;
import com.sample.androidwear.samplewatchapp.SettingsActivity;
import com.sample.androidwear.samplewatchapp.fragment.ActionFragment;

public class AppGridPagerAdapter extends FragmentGridPagerAdapter{

    private Context mContext;

    public AppGridPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getFragment(int row, int column) {
        if(row == 0 && column == 0) {
            CardFragment fragment = CardFragment.create("Quote", "Current quote of the day");
            fragment.setExpansionEnabled(true);
            return fragment;
        } else if(row == 0 && column == 1) {
            return ActionFragment.create("Setting", R.drawable.ic_settings, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, SettingsActivity.class));
                }
            });
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int i) {
        return 2;
    }

}
