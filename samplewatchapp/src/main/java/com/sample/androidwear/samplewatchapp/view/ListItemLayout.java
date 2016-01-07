package com.sample.androidwear.samplewatchapp.view;

import android.content.Context;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sample.androidwear.samplewatchapp.R;

public class ListItemLayout extends LinearLayout implements WearableListView.OnCenterProximityListener {

    private static final float SMALL_RADIUS = 20;
    private static final float BIG_RADIUS = 24;

    private CircledImageView mCircledImageView;
    private TextView mTextView;

    public ListItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemLayout(Context context) {
        this(context, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mCircledImageView = (CircledImageView) findViewById(R.id.circled_image);
        mTextView = (TextView) findViewById(R.id.text);
        mCircledImageView.setCircleRadius(SMALL_RADIUS);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setColor(int color) {
        mCircledImageView.setCircleColor(color);
    }

    public int getColor() {
        return mCircledImageView.getDefaultCircleColor();
    }

    @Override
    public void onCenterPosition(boolean b) {
        mCircledImageView.setCircleRadius(BIG_RADIUS);
    }

    @Override
    public void onNonCenterPosition(boolean b) {
        mCircledImageView.setCircleRadius(SMALL_RADIUS);
    }
}
