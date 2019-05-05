package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;

public class AdminActivity extends BaseActivity implements View.OnClickListener {
    Button btAdmUser;
    Button btAdmAdvice;
    Button btAdmAdd;
    Button btAdmBack;
    TextView tvAdmUserName;
    User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        curUser = (User) getIntent().getSerializableExtra("curUser");
        bindView();

    }

    private void bindView() {
        btAdmAdd = findViewById(R.id.bt_adm_add);
        btAdmAdvice = findViewById(R.id.bt_adm_device);
        btAdmBack = findViewById(R.id.bt_adm_back);
        btAdmUser = findViewById(R.id.bt_adm_user);
        tvAdmUserName = findViewById(R.id.tv_adm_username);
        btAdmUser.setOnClickListener(this);
        btAdmAdd.setOnClickListener(this);
        btAdmAdvice.setOnClickListener(this);
        btAdmBack.setOnClickListener(this);
        tvAdmUserName.setText(curUser.getName()+",欢迎您。");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_adm_add:

                break;
            case R.id.bt_adm_back:
                break;
            case R.id.bt_adm_device:
                break;
            case R.id.bt_adm_user:
                Bundle bundle = new Bundle();
                bundle.putSerializable("curUser",curUser);
                Intent intent = new Intent(this,UserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
