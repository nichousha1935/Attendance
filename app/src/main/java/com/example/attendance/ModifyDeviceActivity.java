package com.example.attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.attendance.view.MyToolBar;

public class ModifyDeviceActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setSteepStatusBar(true);
        setScreenRoate(true);
        super.onCreate(savedInstanceState);
        setActivityContentView(R.layout.activity_modify_device);
        getToolBar().setTitle("系统设置");
        getToolBar().setLeftVisible(true);
        getToolBar().setRight(false);
        getToolBar().setToolBarLeftOnClickListener(new MyToolBar.ToolBarLeftOnClickListener() {
            @Override
            public void setToolBarLeftOnClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void initBind() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View v) {

    }
}
