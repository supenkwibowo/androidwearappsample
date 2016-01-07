package com.sample.androidwear.samplewatchapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.sample.androidwear.samplewatchapp.R;
import com.sample.androidwear.samplewatchapp.adapter.SettingListAdapter;
import com.sample.androidwear.samplewatchapp.preference.SettingsPrefUtils;

public class SettingsActivity extends WearableActivity {

    private TextView mHeaderView;
    private WearableListView mListView;
    private GoogleApiClient mGoogleApiClient;

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
                saveSetting(color);
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
                if(i > 0) {
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
        mGoogleApiClient = createGoogleApiClient();
        mGoogleApiClient.connect();
    }

    private GoogleApiClient createGoogleApiClient() {
        return new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
    }

    private void saveSetting(int color) {
        PutDataMapRequest mapRequest = PutDataMapRequest.create("/settings");
        mapRequest.getDataMap().putInt("color", color);
        PutDataRequest dataRequest = mapRequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(mGoogleApiClient, dataRequest);
    }

}
