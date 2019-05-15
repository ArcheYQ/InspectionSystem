package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.adapter.DeviceAdapter;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.DeviceDataUtil;

public class DeviceActivity extends BaseActivity {
    RecyclerView rvDevice;
    DeviceDataUtil deviceDataUtil;
    DeviceAdapter deviceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        bindView();
        setToolBar(R.id.tb_device);
        initHome();
    }

    private void bindView() {
        rvDevice = findViewById(R.id.rv_device);
        deviceDataUtil = new DeviceDataUtil(this);
        deviceAdapter = new DeviceAdapter(deviceDataUtil.getDevices(true),this,false);
        rvDevice.setAdapter(deviceAdapter);
        rvDevice.setItemAnimator(new DefaultItemAnimator());
        rvDevice.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 在菜单栏中添加"新增设备"按钮
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adddevice,menu);
        return true;
    }

    /**
     * 当"新增设备"被选中时触发的事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_adddevice){
            startActivity(AddDeviceActivity.class,null,false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 重新加载界面时，可能数据已经发生改变，需要刷新视图
     */
    @Override
    protected void onResume() {
        super.onResume();
        deviceAdapter.setList(deviceDataUtil.getDevices(true));
    }
}
