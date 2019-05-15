package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.DeviceDataUtil;
import com.suke.widget.SwitchButton;

public class AddDeviceActivity extends BaseActivity implements View.OnClickListener {
    EditText etAddDeviceName;
    EditText etAddDeviceLocation;
    EditText etAddDeviceNumber;
    Button btAddDevice;
    DeviceDataUtil deviceDataUtil;
    SwitchButton switchButton;
    Device device;

    /**
     * 创建活动时进行初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        setToolBar(R.id.tb_addDevice);
        device = new Device();
        initHome();
        bindView();
        deviceDataUtil = new DeviceDataUtil(this);
    }

    /**
     * 将组件与视图进行绑定，并且设置switch按钮状态改变时触发的事件
     */
    private void bindView() {
        etAddDeviceName = findViewById(R.id.et_adddevice_name);
        etAddDeviceLocation = findViewById(R.id.et_addDevice_location);
        etAddDeviceNumber = findViewById(R.id.et_addDevice_number);
        btAddDevice = findViewById(R.id.bt_addDevice);
        switchButton = findViewById(R.id.switch_button);
        switchButton.setChecked(device.isPublic());
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                device.setPublic(isChecked? 1:0);
            }
        });
        btAddDevice.setOnClickListener(this);
    }

    /**
     * 设置鼠标单击时触发事件
     * @param v 被触发的控件
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_addDevice) {
            String name = etAddDeviceName.getText().toString();
            String location = etAddDeviceLocation.getText().toString();
            String number = etAddDeviceNumber.getText().toString();
            if (name.isEmpty() || location.isEmpty() || number.isEmpty()) {
                Toast.makeText(mActivity, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                return;
            }
            device.setName(name);
            device.setAdmAccount(getCurUser().getAccount());
            device.setNumber(number);
            device.setLocation(location);
            device.setAddTime(System.currentTimeMillis());
            deviceDataUtil.addDevice(device);
            finish();
        }
    }
}
