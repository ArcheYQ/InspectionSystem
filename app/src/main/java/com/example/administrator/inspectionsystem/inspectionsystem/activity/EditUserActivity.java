package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EditUserActivity extends BaseActivity implements View.OnClickListener {
    EditText etEditPassword;
    TextView tvEditAccount;
    TextView tvEditUsername;
    Button btEditUser;
    Button btDeleteUser;
    UserDataUtil userDataUtil;
    User editUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        setToolBar(R.id.tb_editUser);
        initHome();
        editUser = (User) getIntent().getSerializableExtra("editUser");
        bindView();
        userDataUtil = new UserDataUtil(this);
    }

    private void bindView() {
        etEditPassword = findViewById(R.id.et_edituser_password);
        tvEditAccount = findViewById(R.id.tv_edituser_account);
        tvEditUsername = findViewById(R.id.tv_edituser_username);
        btEditUser = findViewById(R.id.bt_editUser);
        btDeleteUser = findViewById(R.id.bt_deleteUser);
        if(editUser.getRole().equals(Role.ADMIN)){
            btDeleteUser.setClickable(true);
            btDeleteUser.setVisibility(View.VISIBLE);
        }
        btDeleteUser.setOnClickListener(this);
        btEditUser.setOnClickListener(this);
        etEditPassword.setText(editUser.getPassword());
        tvEditAccount.setText(editUser.getAccount());
        tvEditUsername.setText(editUser.getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_editUser:
                if(etEditPassword.getText().toString().isEmpty()){
                    Toast.makeText(mActivity, "请填写修改后的密码", Toast.LENGTH_SHORT).show();
                    break;
                }
                editUser.setPassword(etEditPassword.getText().toString());
                userDataUtil.editUser(editUser);
                Toast.makeText(mActivity, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.bt_deleteUser:
                userDataUtil.deleteInspector(editUser.getAccount());
                Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
