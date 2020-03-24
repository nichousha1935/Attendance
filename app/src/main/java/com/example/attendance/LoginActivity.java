package com.example.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.attendance.okhttp.CallBackUtil;
import com.example.attendance.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_device_name;//设备名称
    private EditText et_classroom;//绑定教室
    private EditText et_device_ip;//设备IP
    private EditText et_port_id;//端口号
    private Button btn_login;//登录
    private TextView tv_modify_device;//修改设备信息
    private long time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setSteepStatusBar(true);
        setScreenRoate(true);
        super.onCreate(savedInstanceState);
        setActivityContentView(R.layout.activity_login);
        getToolBar().setTitle("智慧班牌");
        getToolBar().setLeftVisible(false);
        getToolBar().setRight(false);
    }

    @Override
    public void initView() {
        et_device_name = findViewById(R.id.et_device_name);
        et_classroom = findViewById(R.id.et_classroom);
        et_device_ip = findViewById(R.id.et_device_ip);
        et_port_id = findViewById(R.id.et_port_id);
        btn_login = findViewById(R.id.btn_login);
        tv_modify_device = findViewById(R.id.tv_modify_device);
    }

    @Override
    public void initBind() {
        btn_login.setOnClickListener(this::onClick);
        tv_modify_device.setOnClickListener(this::onClick);
    }

    @Override
    public void initData() {
        setHint("设备2352364", et_device_name);
        setHint("教学楼#A101", et_classroom);
        setHint("请输入IP", et_device_ip);
        setHint("请输入端口号", et_port_id);
        getInfo();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_modify_device:
                startActivity(new Intent(LoginActivity.this, ModifyDeviceActivity.class));
                break;
            default:
                break;
        }
    }


    public void setHint(String name, EditText editText) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(name);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 2000) {
                toast(this, "再按一次退出程序");
                time = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return false;
    }

    public void getInfo() {
        String url = Constants.IP + "skip";
        Map<String, String> map = new HashMap<>();
        map.put("uid", "123");

        Map<String, String> heaherMap = new HashMap<>();
        heaherMap.put("content-type", "application/json");
        OkhttpUtil.okHttpGet(url, map, heaherMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                String msg = JSON.parseObject(response).getString("msg");
                if (msg.equals("success")) {
                    String d = JSON.parseObject(response).getString("d");
                    switch (d) {
                        case "1"://课程页面
                            break;
                        case "2"://考试页面
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }
}
