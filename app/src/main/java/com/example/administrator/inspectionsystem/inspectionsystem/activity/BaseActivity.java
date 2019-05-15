package com.example.administrator.inspectionsystem.inspectionsystem.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Role;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;


public class BaseActivity extends AppCompatActivity {
    /**
     * toolbar
     */
    public Toolbar mToolbar;

    public Activity mActivity;

    private static final String PACKAGE_URL_SCHEME = "package:";//权限方案

    public static final int PERMISSION_DENIEG = 1;//权限不足，权限被拒绝的时候

    /**
     * 初始化菜单栏
     * @param toolId
     */
    public void setToolBar(int toolId){
        Toolbar toolbar = (Toolbar) findViewById(toolId);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            this.mToolbar=toolbar;
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 将该角色存到SharedPreferences中，方便随时查看当前登录用户
     * @param user
     */
    public void setCurUser(User user){
        SharedPreferences userSettings = getSharedPreferences("curUser", 0);
        SharedPreferences.Editor editor = userSettings.edit();
        editor.putString("name",user.getName());
        editor.putInt("ROLE",user.getRole().value);
        editor.putString("account",user.getAccount());
        editor.putString("password",user.getPassword());
        editor.commit();
    }

    /**
     * 获得当前登录的账号信息
     * @return
     */
    public User getCurUser(){
        User cur = new User();
        SharedPreferences userSettings= getSharedPreferences("curUser", 0);
        cur.setAccount(userSettings.getString("account","default"));
        cur.setPassword(userSettings.getString("password","default"));
        cur.setName(userSettings.getString("name","default"));
        cur.setRole(Role.getRole(userSettings.getInt("ROLE",1)));
        return cur;
    }

    /**
     * 退出登录时，清空当前角色的登录信息
     */
    public void cleanUser(){
        SharedPreferences userSettings= getSharedPreferences("curUser", 0);
        SharedPreferences.Editor editor = userSettings.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 设置返回上一页的箭头（页面左上角）
     */
    public void initHome(){
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.mipmap.black_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }


    /**
     * 当缺少系统权限时，对用户进行提示
     * @param requestCode
     * @param grantResults
     */
    private void showMissingPermissionDialog(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("帮助");//提示帮助
                builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限。\\n\\n最后点击两次后退按钮，即可返回。");

                //如果是拒绝授权，返回
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(PERMISSION_DENIEG);//权限不足
                        Toast.makeText(mActivity, "取消", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //跳转系统应用权限
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
                        startActivity(intent);
                        return;
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        }
    }


    /**
     * 自定义打开另外一个活动的函数，调用更加方便
     * @param target
     * @param bundle
     * @param finish
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(getPackageName(), bundle);
        startActivity(intent);
        if (finish)
            finish();
    }

    /**
     * 保存权限请求的结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        showMissingPermissionDialog(requestCode, grantResults);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mActivity = this;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mActivity = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();//返回
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
