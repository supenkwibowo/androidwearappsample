package com.sample.androidwear.samplewatchapp.preference;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;

public class SettingsPrefUtils {

    public static void setColor(Context context, int color) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putInt("color", color)
                .commit();
    }

    public static int getColor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt("color", Color.WHITE);
    }

}
