package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;

public class AddUserActivity extends BaseActivity implements View.OnClickListener {
    EditText etAddUserAccount;
    EditText etAddUserPassword;
    EditText etAddUserUsername;
    Button btAddUser;
    UserDataUtil userDataUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setToolBar(R.id.tb_addUser);
        initHome();
        bindView();
        userDataUtil = new UserDataUtil(this);
    }

    private void bindView() {
        etAddUserAccount = findViewById(R.id.et_addUser_account);
        etAddUserPassword = findViewById(R.id.et_addUser_password);
        etAddUserUsername = findViewById(R.id.et_addUser_username);
        btAddUser = findViewById(R.id.bt_addUser);
        btAddUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addUser:
                String account = etAddUserAccount.getText().toString();
                String password = etAddUserPassword.getText().toString();
                String username = etAddUserUsername.getText().toString();
                if (account.isEmpty() || password.isEmpty() || username.isEmpty()){
                    Toast.makeText(mActivity, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    break;
                }
                User user = new User();
                user.setRole(Role.INSPECTOR);
                user.setName(username);
                user.setPassword(password);
                user.setAccount(account);
                userDataUtil.addInspector(user);
                Toast.makeText(mActivity, "添加成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
