package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.inspectionsystem.R;

public class AdviceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        bindView();
        setToolBar(R.id.tb_advice);
        initHome();
    }

    private void bindView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addadvice,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_addadvice){
            startActivity(AddAdviceActivity.class,null,false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
