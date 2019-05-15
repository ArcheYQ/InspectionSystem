package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 把系统通知栏设置为透明
        StatusBarCompat.translucentStatusBar(this);
        // 一开始打开欢迎页
        setContentView(R.layout.activity_main);

        // 设置延迟1秒钟，防止一开始软件初始化过程中出现白屏
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getCurUser().getAccount().equals("default")){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this, AdminActivity.class));

                }
                finish();
                }
        }, 1000);
    }
}
