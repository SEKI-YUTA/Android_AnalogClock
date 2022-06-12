package com.example.simpleanalogclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    Button btn_bgWhite, btn_bgBlack, btn_bgCustomColor;
    SettingManager settingManager;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_bgWhite = findViewById(R.id.btn_bgWhite);
        btn_bgBlack = findViewById(R.id.btn_bgBlack);
        btn_bgCustomColor = findViewById(R.id.btn_bgCustomColor);
        settingManager = SettingManager.getInstance();
        preferences = getApplicationContext().getSharedPreferences("Setting", 0);
        editor = preferences.edit();

        btn_bgWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingManager.changeBgColor("#FFFFFF");
                editor.putString("bgColor", "#FFFFFF");
                editor.apply();
            }
        });
        btn_bgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingManager.changeBgColor("#000000");
                editor.putString("bgColor", "#000000");
                editor.apply();
            }
        });

        btn_bgCustomColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}