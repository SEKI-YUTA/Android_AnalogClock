package com.example.simpleanalogclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

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
                ColorPickerDialogBuilder
                        .with(SettingActivity.this)
                        .setTitle("Choose color")
                        .initialColor(Color.RED)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Toast.makeText(SettingActivity.this, "You Selected" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                                changeBackgroundColor(selectedColor);
                                Log.d("MyLog", "change bg to " + Integer.toHexString(selectedColor));
                                String newColor = Integer.toHexString(selectedColor);
                                settingManager.changeBgColor("#" + newColor);
                                editor.putString("bgColor", "#" + newColor);
                                editor.apply();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });
    }

}