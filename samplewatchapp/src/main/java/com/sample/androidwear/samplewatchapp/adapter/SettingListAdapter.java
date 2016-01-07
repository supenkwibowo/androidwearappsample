package com.sample.androidwear.samplewatchapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.view.ViewGroup;

import com.sample.androidwear.samplewatchapp.R;
import com.sample.androidwear.samplewatchapp.view.ListItemLayout;

public class SettingListAdapter extends WearableListView.Adapter {

    private static final String[] COLOR_LABELS = {
            "White", "Red", "Green", "Blue"
    };
    private static final int[] COLORS = {
            Color.WHITE, Color.RED, Color.GREEN, Color.BLUE
    };

    private Context mContext;

    public SettingListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(View.inflate(mContext, R.layout.item_setting, null));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.getListItemLayout().setColor(COLORS[position]);
        itemViewHolder.getListItemLayout().setText(COLOR_LABELS[position]);
    }

    @Override
    public int getItemCount() {
        return COLORS.length;
    }

    public static class ItemViewHolder extends WearableListView.ViewHolder {

        private ListItemLayout mListItemLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mListItemLayout = (ListItemLayout) itemView;
        }

        public ListItemLayout getListItemLayout() {
            return mListItemLayout;
        }
    }
}
