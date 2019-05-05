package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.inspectionsystem.R;

public class DeviceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        bindView();
        setToolBar(R.id.tb_device);
        initHome();
    }

    private void bindView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adddevice,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_adddevice){
            startActivity(AddDeviceActivity.class,null,false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
