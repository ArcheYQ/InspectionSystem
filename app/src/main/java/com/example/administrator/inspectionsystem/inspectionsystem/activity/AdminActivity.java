package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;

public class AdminActivity extends BaseActivity implements View.OnClickListener {
    Button btAdmUser;
    Button btAdmDevice;
    Button btAdmAdd;
    Button btAdmBack;
    TextView tvAdmUserName;
    User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // 获得当前登录用户的信息
        curUser = getCurUser();
        bindView();

    }

    private void bindView() {
        btAdmAdd = findViewById(R.id.bt_adm_add);
        btAdmDevice = findViewById(R.id.bt_adm_device);
        btAdmBack = findViewById(R.id.bt_adm_back);
        btAdmUser = findViewById(R.id.bt_adm_user);
        tvAdmUserName = findViewById(R.id.tv_adm_username);
        btAdmUser.setOnClickListener(this);
        btAdmAdd.setOnClickListener(this);
        btAdmDevice.setOnClickListener(this);
        btAdmBack.setOnClickListener(this);
        tvAdmUserName.setText(curUser.getName()+",欢迎您。");
        // 如果角色是ADMIN，才具有"管理设备"按钮
        if (getCurUser().getRole().equals(Role.ADMIN)){
            btAdmDevice.setVisibility(View.VISIBLE);
            btAdmDevice.setClickable(true);
            btAdmUser.setVisibility(View.VISIBLE);
            btAdmUser.setClickable(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_adm_add:
                startActivity(RegisterActivity.class,null,false);
                break;
            case R.id.bt_adm_back:
                cleanUser();
                startActivity(LoginActivity.class,null,true);
                break;
            case R.id.bt_adm_device:
                startActivity(DeviceActivity.class,null,false);
                break;
            case R.id.bt_adm_user:
                startActivity(UserActivity.class,null,false);
                break;
        }
    }
}
