package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Register;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.RegisterDataUtil;

public class AddRegisterActivity extends BaseActivity implements View.OnClickListener {
    EditText etAddRegisterTemperature;
    EditText etAddRegisterPressure;
    EditText etAddRegisterComment;
    Button btAddRegister;
    Device device;
    RegisterDataUtil registerDataUtil;

    /**
     * 创建活动时进行初始化
     * @param savedInstanceState
     */
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

    /**
     * 将组件与视图进行绑定
     */
    private void bindView() {
        etAddRegisterTemperature = findViewById(R.id.et_addRegister_temperature);
        etAddRegisterPressure = findViewById(R.id.et_addRegister_pressure);
        etAddRegisterComment = findViewById(R.id.et_addRegister_comment);
        btAddRegister = findViewById(R.id.bt_addRegister);
        btAddRegister.setOnClickListener(this);
    }

    /**
     * 设置鼠标单击时触发事件
     * @param v 被触发的控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 登记信息点击 确定 时对应的事件
            case R.id.bt_addRegister:
                String temperature = etAddRegisterTemperature.getText().toString();
                String pressure = etAddRegisterPressure.getText().toString();
                String comment = etAddRegisterComment.getText().toString();
                if (temperature.isEmpty() || pressure.isEmpty() || comment.isEmpty()){
                    Toast.makeText(mActivity, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    break;
                }
                Register register = new Register();
                register.setOperatorName(getCurUser().getName());
                register.setTemperature(temperature);
                register.setDeviceId(device.getId());
                register.setTime(System.currentTimeMillis());
                register.setPressure(pressure);
                register.setComment(comment);
                register.setOperatorAccount(getCurUser().getAccount());
                registerDataUtil.add(register);
                Toast.makeText(mActivity, "登记成功", Toast.LENGTH_SHORT).show();
                finish();
        }
    }
}
