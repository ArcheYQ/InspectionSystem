package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.adapter.UserAdapter;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
   FloatingActionButton fbAddUser;
   TextView tvCurAccount;
   TextView tvCurUserName;
   Button btCurUserEdit;
   RecyclerView rvUserList;
   UserDataUtil userDataUtil;
   User curUser;
   UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bindView();
        initData();
    }

    private void initData() {
        curUser = (User) getIntent().getSerializableExtra("curUser");
        tvCurAccount.setText("当前账号：" + curUser.getAccount().toString());
        tvCurUserName.setText("当前用户名：" + curUser.getName().toString());
        userDataUtil = new UserDataUtil(this);
        userAdapter = new UserAdapter(userDataUtil.getInspectors());
        rvUserList.setAdapter(userAdapter);
        rvUserList.setItemAnimator(new DefaultItemAnimator());
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void bindView() {
        fbAddUser = findViewById(R.id.fb_user);
        tvCurAccount = findViewById(R.id.tv_user_account);
        tvCurUserName = findViewById(R.id.tv_user_username);
        btCurUserEdit = findViewById(R.id.bt_user_edit);
        rvUserList = findViewById(R.id.rv_user);
        btCurUserEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
