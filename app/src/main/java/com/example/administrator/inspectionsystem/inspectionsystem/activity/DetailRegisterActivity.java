package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.adapter.RegisterAdapter;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.RegisterDataUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailRegisterActivity extends BaseActivity implements View.OnClickListener {
    TextView tvAdmAccount;
    TextView tvNumber;
    TextView tvName;
    TextView tvAddTime;
    TextView tvLocation;
    RecyclerView rvDetailRegister;
    FloatingActionButton fbRegister;
    Device device;
    RegisterAdapter registerAdapter;
    RegisterDataUtil registerDataUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);
        setToolBar(R.id.tb_detail_register);
        device = (Device) getIntent().getSerializableExtra("editDevice");
        initHome();
        bindView();
        initData();
    }

    private void initData() {
        tvAdmAccount.setText("管理人员：" + device.getAdmAccount().toString());
        tvNumber.setText("设备编号：" + device.getNumber().toString());
        tvName.setText("设备名称：" + device.getName().toString());
        tvAddTime.setText("添加时间：" + formatTime("yyyy-MM-dd HH:mm",device.getAddTime()));
        tvLocation.setText("设备位置：" + device.getLocation().toString());
        registerDataUtil = new RegisterDataUtil(this);
        registerAdapter = new RegisterAdapter(registerDataUtil.getRegisters(device.getId()));
        rvDetailRegister.setAdapter(registerAdapter);
        rvDetailRegister.setItemAnimator(new DefaultItemAnimator());
        rvDetailRegister.setLayoutManager(new LinearLayoutManager(this));
    }

    private void bindView() {
        tvAdmAccount = findViewById(R.id.tv_register_device_admaccount);
        tvNumber = findViewById(R.id.tv_register_device_number);
        tvName = findViewById(R.id.tv_register_device_name);
        tvAddTime = findViewById(R.id.tv_register_device_addtime);
        tvLocation = findViewById(R.id.tv_register_device_location);
        rvDetailRegister = findViewById(R.id.rv_detail_register);
        fbRegister = findViewById(R.id.fb_register);
        fbRegister.setOnClickListener(this);
    }
    public static String formatTime(String format, long time)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }
    protected void onResume() {
        super.onResume();
        registerAdapter.setList(registerDataUtil.getRegisters(device.getId()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fb_register:
                Intent intent = new Intent(this,AddRegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("registerDevice",device);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
