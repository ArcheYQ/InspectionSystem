package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.DeviceDataUtil;

public class EditDeviceActivity extends BaseActivity implements View.OnClickListener {
    TextView tvDeviceNumber;
    EditText etDeviceName;
    EditText etDeviceLocation;
    Button btEdit;
    Button btDelete;
    Device device;
    DeviceDataUtil deviceDataUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);
        setToolBar(R.id.tb_editDevice);
        initHome();
        // 通过getIntent获得上一个活动通过bundle传递过来的数据
        device = (Device) getIntent().getSerializableExtra("editDevice");
        deviceDataUtil = new DeviceDataUtil(this);
        bindView();
    }

    private void bindView() {
        tvDeviceNumber = findViewById(R.id.tv_editdevice_number);
        etDeviceName = findViewById(R.id.et_editdevice_name);
        etDeviceLocation = findViewById(R.id.et_editdevice_location);
        btEdit = findViewById(R.id.bt_editDevice);
        btDelete = findViewById(R.id.bt_deleteDevice);
        etDeviceLocation.setText(device.getLocation());
        etDeviceName.setText(device.getName());
        btEdit.setOnClickListener(this);
        btDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_editDevice:
                String name = etDeviceName.getText().toString();
                String location = etDeviceLocation.getText().toString();
                if(name.isEmpty() || location.isEmpty()){
                    Toast.makeText(this, "信息没有填写完整", Toast.LENGTH_SHORT).show();
                    break;
                }
                device.setName(name);
                device.setLocation(location);
                deviceDataUtil.editDevice(device);
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.bt_deleteDevice:
                deviceDataUtil.deleteDevice(device);
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
