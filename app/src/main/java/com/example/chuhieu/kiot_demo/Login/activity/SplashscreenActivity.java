package com.example.chuhieu.kiot_demo.Login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.chuhieu.kiot_demo.R;

public class SplashscreenActivity extends AppCompatActivity {
    private int Time_out = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashscreenActivity.this,LoginActivity.class));
                finish();
            }
        },Time_out);
    }


}
