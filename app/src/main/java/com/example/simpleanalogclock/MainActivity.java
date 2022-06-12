package com.example.simpleanalogclock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.simpleanalogclock.Listeners.OnChangeSettingListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    AnalogClock my_analogClock;
    RelativeLayout root_layout;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Date prevBackPressed;

    SettingManager settingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_analogClock = findViewById(R.id.my_analogClock);
        root_layout = findViewById(R.id.root_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        SettingManager.setUpManager(this);
        settingManager = SettingManager.getInstance();


        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Setting", 0);
        String colorCode = preferences.getString("bgColor", "#F1F1F1");
        Log.d("MyLog", colorCode);
        int colorInt = Color.parseColor(colorCode);
        root_layout.setBackgroundColor(colorInt);

//        Drawable bgDrawable = new Drawable() {
//            @Override
//            public void draw(@NonNull Canvas canvas) {
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clock_dialog2);
//                canvas.setBitmap(bitmap);
//            }
//
//            @Override
//            public void setAlpha(int i) {
//
//            }
//
//            @Override
//            public void setColorFilter(@Nullable ColorFilter colorFilter) {
//
//            }
//
//            @Override
//            public int getOpacity() {
//                return PixelFormat.OPAQUE;
//            }
//        };
//        my_analogClock.setBackground(bgDrawable);

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Date now = new Date();
        if(prevBackPressed != null) {
            if((now.getTime() - prevBackPressed.getTime()) < 2000) {
                super.onBackPressed();
                return;
            }
        }
        prevBackPressed = now;
        Toast.makeText(this, "アプリを終了するには素早く二回押してください", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu  ,menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_option_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                intent.putExtra("manager", settingManager);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}