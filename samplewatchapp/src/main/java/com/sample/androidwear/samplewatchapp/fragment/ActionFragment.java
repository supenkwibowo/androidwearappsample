package com.sample.androidwear.samplewatchapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.wearable.view.ActionPage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.androidwear.samplewatchapp.R;
import com.sample.androidwear.samplewatchapp.adapter.AppGridPagerAdapter;

/**
 * Created by supenk on 1/8/16.
 */
public class ActionFragment extends Fragment {

    private View.OnClickListener mClickListener;

    public static ActionFragment create(String text, int imageResId, View.OnClickListener clickListener) {
        ActionFragment actionFragment = new ActionFragment();
        actionFragment.mClickListener = clickListener;
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        bundle.putInt("image", imageResId);
        actionFragment.setArguments(bundle);
        return actionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionPage actionPage = (ActionPage) inflater.inflate(R.layout.fragment_action, container, false);
        actionPage.setOnClickListener(mClickListener);

        Bundle arguments = getArguments();
        if (arguments != null) {
            actionPage.setText(arguments.getString("text"));
            int imageResId = arguments.getInt("image");
            if (imageResId != 0) {
                actionPage.setImageResource(imageResId);
            }
        }
        return actionPage;
    }

}
