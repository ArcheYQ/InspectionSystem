package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Register;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.DeviceDataUtil;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.RegisterDataUtil;

public class AddRegisterActivity extends BaseActivity implements View.OnClickListener {
    EditText etAddRegisterTemperature;
    EditText etAddRegisterPressure;
    Button btAddRegister;
    Device device;
    RegisterDataUtil registerDataUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_register);
        setToolBar(R.id.tb_addRegister);
        device = (Device) getIntent().getSerializableExtra("registerDevice");
        initHome();
        bindView();
        registerDataUtil = new RegisterDataUtil(this);
    }

    private void bindView() {
        etAddRegisterTemperature = findViewById(R.id.et_addRegister_temperature);
        etAddRegisterPressure = findViewById(R.id.et_addRegister_pressure);
        btAddRegister = findViewById(R.id.bt_addRegister);
        btAddRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addRegister:
                String temperature = etAddRegisterTemperature.getText().toString();
                String pressure = etAddRegisterPressure.getText().toString();
                if (temperature.isEmpty() || pressure.isEmpty()){
                    Toast.makeText(mActivity, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    break;
                }
                Register register = new Register();
                register.setOperatorName(getCurUser().getName());
                register.setTemperature(temperature);
                register.setDeviceId(device.getId());
                register.setTime(System.currentTimeMillis());
                register.setPressure(pressure);
                register.setOperatorAccount(getCurUser().getAccount());
                registerDataUtil.add(register);
                Toast.makeText(mActivity, "登记成功", Toast.LENGTH_SHORT).show();
                finish();
        }
    }
}
