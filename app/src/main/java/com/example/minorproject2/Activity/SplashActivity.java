package com.example.minorproject2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.minorproject2.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
                Intent intent;
                if (isLoggedIn==true) {
                    // User is already logged in, navigate to the main activity or home screen
                    intent=new Intent(SplashActivity.this, MainActivity.class);

                }else{
                    intent=new Intent(SplashActivity.this, loginActivity.class);

                }
                startActivity(intent);
                finish();
            }
        },4000);
    }
}