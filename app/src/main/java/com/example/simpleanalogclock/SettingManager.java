package com.example.simpleanalogclock;

import android.app.Activity;
import android.graphics.Color;

import java.io.Serializable;

public class SettingManager implements Serializable {
    private static SettingManager instance = new SettingManager();
    private static Activity targetActivity;

    public static SettingManager getInstance() {
        return instance;
    }

    public static void setUpManager(Activity activity) {
        targetActivity = activity;
    }


    public void changeBgColor(String colorCode) {
        int colorInt = Color.parseColor(colorCode);
        targetActivity.findViewById(R.id.root_layout).setBackgroundColor(colorInt);

    }
}
