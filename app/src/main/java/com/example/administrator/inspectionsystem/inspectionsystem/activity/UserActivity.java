package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.adapter.UserAdapter;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;

public class UserActivity extends BaseActivity implements View.OnClickListener {
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
        setToolBar(R.id.tb_user);
        initHome();
    }

    private void initData() {
        curUser = getCurUser();
        tvCurAccount.setText("当前账号：" + curUser.getAccount().toString());
        tvCurUserName.setText("当前用户名：" + curUser.getName().toString());
        userDataUtil = new UserDataUtil(this);
        userAdapter = new UserAdapter(userDataUtil.getInspectors(),this);
        rvUserList.setAdapter(userAdapter);
        rvUserList.setItemAnimator(new DefaultItemAnimator());
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void bindView() {
        tvCurAccount = findViewById(R.id.tv_user_account);
        tvCurUserName = findViewById(R.id.tv_user_username);
        btCurUserEdit = findViewById(R.id.bt_user_edit);
        rvUserList = findViewById(R.id.rv_user);
        btCurUserEdit.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adduser,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_adduser){
            startActivity(AddUserActivity.class,null,false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_user_edit:
                Intent intent = new Intent(this, EditUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("editUser",curUser);
                intent.putExtras(bundle);
                this.startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        curUser = userDataUtil.getUserFromAccount(curUser.getAccount());
        userAdapter.setList(userDataUtil.getInspectors());
    }
}
