package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    EditText etLoginPassword;
    EditText etLoginAccount;
    RadioButton rbLoginAdm;
    RadioButton rbLoginIns;
    Button btLogin;
    RadioGroup rgChoose;
    int curRole;
    UserDataUtil userDataUtil;
    User curUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();
        userDataUtil = new UserDataUtil(this);
        rbLoginAdm.setChecked(true);
        curRole = Role.ADMIN.value;
        rgChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.rb_login_administrato){
                    curRole = Role.ADMIN.value;
                }else {
                    curRole = Role.INSPECTOR.value;
                }
            }
        });
    }

    private void bindView() {
        etLoginAccount = findViewById(R.id.et_login_account);
        etLoginPassword = findViewById(R.id.et_login_password);
        rbLoginAdm = findViewById(R.id.rb_login_administrato);
        rbLoginIns = findViewById(R.id.rb_login_inspector);
        btLogin = findViewById(R.id.bu_login);
        btLogin.setOnClickListener(this);
        rgChoose = findViewById(R.id.rg_login_choose);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bu_login :
                String password = etLoginPassword.getText().toString();
                String account = etLoginAccount.getText().toString();
                if (password.isEmpty() || account.isEmpty()){
                    Toast.makeText(mActivity, "请填写完整账号信息", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    curUser = userDataUtil.getUserFromAccount(account);
                }
                if (curUser == null) {
                    Toast.makeText(mActivity, "账号不存在", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (curUser.getRole().value != curRole){
                    Toast.makeText(mActivity, "角色验证失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!curUser.getPassword().equals(password)){
                    Toast.makeText(mActivity, "密码错误", Toast.LENGTH_SHORT).show();
                    break;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("curUser",curUser);
                if(curRole == Role.ADMIN.value)  {
                    Intent intent = new Intent(this,AdminActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {

                }
                finish();
                break;
        }
    }
}
