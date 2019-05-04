package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;


import com.example.administrator.inspectionsystem.R;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        setContentView(R.layout.activity_main);
    }
}
