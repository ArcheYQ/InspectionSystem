package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Advice;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.AdviceDataUtil;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.UserDataUtil;
import com.suke.widget.SwitchButton;

public class AddAdviceActivity extends BaseActivity implements View.OnClickListener {
    EditText etAddAdviceName;
    EditText etAddAdviceLocation;
    EditText etAddAdviceNumber;
    Button btAddAdvice;
    AdviceDataUtil adviceDataUtil;
    SwitchButton switchButton;
    Advice advice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advice);
        setToolBar(R.id.tb_addAdvice);
        initHome();
        bindView();
        adviceDataUtil = new AdviceDataUtil(this);
    }

    private void bindView() {
        etAddAdviceName = findViewById(R.id.et_addadvice_name);
        etAddAdviceLocation = findViewById(R.id.et_addAdvice_location);
        etAddAdviceNumber = findViewById(R.id.et_addAdvice_number);
        btAddAdvice = findViewById(R.id.bt_addAdvice);
        switchButton = findViewById(R.id.switch_button);
        switchButton.setChecked(advice.isPublic());
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                advice.setPublic(isChecked? 1:0);
            }
        });
        btAddAdvice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addAdvice:
                String name = etAddAdviceName.getText().toString();
                String location = etAddAdviceLocation.getText().toString();
                String number = etAddAdviceNumber.getText().toString();
                if (name.isEmpty() || location.isEmpty() || number.isEmpty()){
                    Toast.makeText(mActivity, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    break;
                }
                advice.setName(name);
                advice.setAdmAccount(getCurUser().getAccount());
                advice.setNumber(number);
                advice.setLocation(location);
                advice.setAddTime(System.currentTimeMillis());
                adviceDataUtil.addAdvice(advice);
                finish();
                break;
        }
    }
}
