package com.example.attendance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendance.view.DMDialog;
import com.example.attendance.view.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private WebView mWVmhtml;
    private ImageView img_mongolian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSteepStatusBar(true);
        setScreenRoate(true);
        super.onCreate(savedInstanceState);
        setActivityContentView(R.layout.activity_main);
        getToolBar().setVisibility(View.GONE);
        //获取控件对象
        //加载本地html文件
        // mWVmhtml.loadUrl("file:///android_asset/hello.html");
        //加载网络URL
        mWVmhtml.loadUrl("http://112.124.200.51:7443/class_card_h5/main.html");
        //设置JavaScrip
        mWVmhtml.getSettings().setJavaScriptEnabled(true);
        //支持通过JS打开新窗口(允许JS弹窗)
        mWVmhtml.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWVmhtml.getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWVmhtml.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        mWVmhtml.getSettings().setMediaPlaybackRequiresUserGesture(true);
        mWVmhtml.getSettings().setAllowFileAccess(true);
        mWVmhtml.getSettings().setPluginState(WebSettings.PluginState.ON);

        mWVmhtml.setBackgroundColor(Color.WHITE);

        //访问百度首页
//        mWVmhtml.loadUrl("http://112.124.200.51:8081/app/index");
        //设置在当前WebView继续加载网页
        mWVmhtml.setWebViewClient(new MyWebViewClient());
    }

    @Override
    public void initView() {
        mWVmhtml = findViewById(R.id.WV_Id);
        img_mongolian = findViewById(R.id.img_mongolian);
    }

    @Override
    public void initBind() {
        img_mongolian.setOnClickListener(this::onClick);
    }

    @Override
    public void initData() {
        img_mongolian.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               // img_mongolian.setVisibility(View.GONE);
                showDialog();
                return false;
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_mongolian:
                break;
            default:
                break;
        }
    }


    class MyWebViewClient extends WebViewClient {
        @Override  //WebView代表是当前的WebView
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //表示在当前的WebView继续打开网页
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("WebView", "开始访问网页");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebView", "访问网页结束");
        }
    }

    public void JS() {
        mWVmhtml.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }

        });
    }

    @JavascriptInterface
    public void gotoLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWVmhtml.canGoBack()) {
            mWVmhtml.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }

    private void showDialog() {
        DMDialog.builder(this, R.layout.login_setting_dialog)
                .onDialogInitListener((helper, dialog) ->
                {
                    helper.setOnClickListener(R.id.btn_login_out, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.showToast(MainActivity.this,"退出登录");
                        }
                    });
                    helper.setOnClickListener(R.id.btn_setting, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.showToast(MainActivity.this,"系统设置");
                        }
                    });
                })
                .setGravity(Gravity.BOTTOM)
                .show();
    }
}
