package com.sample.androidwear.samplewatchapp;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;

import com.sample.androidwear.samplewatchapp.adapter.SettingListAdapter;
import com.sample.androidwear.samplewatchapp.preference.SettingsPrefUtils;

public class SettingsActivity extends WearableActivity {

    private TextView mHeaderView;
    private WearableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mHeaderView = (TextView) findViewById(R.id.header);
        mListView = (WearableListView) findViewById(R.id.list);
        mListView.setAdapter(new SettingListAdapter(this));

        mListView.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                SettingListAdapter.ItemViewHolder itemViewHolder = (SettingListAdapter.ItemViewHolder) viewHolder;
                int color = itemViewHolder.getListItemLayout().getColor();
                SettingsPrefUtils.setColor(getApplicationContext(), color);
                finish();
            }

            @Override
            public void onTopEmptyRegionClick() {
            }
        });
        mListView.addOnScrollListener(new WearableListView.OnScrollListener() {
            @Override
            public void onScroll(int i) {

            }

            @Override
            public void onAbsoluteScrollChange(int i) {
                if (i > 0) {
                    mHeaderView.setY(-i);
                }
            }

            @Override
            public void onScrollStateChanged(int i) {

            }

            @Override
            public void onCentralPositionChanged(int i) {

            }
        });
    }

}
