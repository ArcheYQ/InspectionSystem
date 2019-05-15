package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.adapter.DeviceAdapter;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.DeviceDataUtil;

public class RegisterActivity extends BaseActivity {
    RecyclerView rvRegister;
    DeviceAdapter deviceAdapter;
    DeviceDataUtil deviceDataUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindView();
        setToolBar(R.id.tb_register);
        initHome();
    }

    private void bindView() {
        rvRegister = findViewById(R.id.rv_register);
        deviceDataUtil = new DeviceDataUtil(this);
        deviceAdapter = new DeviceAdapter(deviceDataUtil.getDevices(getCurUser().getRole().equals(Role.ADMIN)),this,true);
        rvRegister.setAdapter(deviceAdapter);
        rvRegister.setItemAnimator(new DefaultItemAnimator());
        rvRegister.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 重新加载界面时，可能数据已经发生改变，需要刷新视图
     */
    @Override
    protected void onResume() {
        super.onResume();
        deviceAdapter.setList(deviceDataUtil.getDevices(getCurUser().getRole().equals(Role.ADMIN)));
    }
}
