package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.example.administrator.inspectionsystem.R;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                }
        }, 1000);
    }
}
