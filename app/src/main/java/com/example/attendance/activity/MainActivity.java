package com.example.attendance.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.attendance.BaseActivity;
import com.example.attendance.R;
import com.example.attendance.dialog.MessageDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private WebView wvContent;
    private MessageDialog messageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageDialog = MessageDialog.newInstance("加载中，请稍等……");
        setActivityContentView(R.layout.activity_main);
        getToolBar().setTitle("生物质电厂互联网温度检测平台");
        getToolBar().setBackgroundColor(Color.parseColor("#54a6fa"));
        getToolBar().setTitleTextColor(Color.parseColor("#FFFFFF"));
        getToolBar().setRightVisible(false);
        getToolBar().setLeftVisible(false);

    }

    public static void startInstant(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        wvContent = findViewById(R.id.wv_content);
        setWebView();
        if (!messageDialog.isVisible()) {
            messageDialog.show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public void initBind() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    public void setWebView() {
        //设置JavaScrip
        wvContent.getSettings().setJavaScriptEnabled(true);
        //支持通过JS打开新窗口(允许JS弹窗)
        wvContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        wvContent.getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        wvContent.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        wvContent.getSettings().setMediaPlaybackRequiresUserGesture(true);
        wvContent.getSettings().setAllowFileAccess(true);
        wvContent.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvContent.getSettings().setDomStorageEnabled(true);
        wvContent.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        wvContent.setBackgroundColor(Color.WHITE);
        //加载网络URL
        wvContent.loadUrl("http://www.ntnywlw.cn/Home/LogInWx");
        //设置在当前WebView继续加载网页
        wvContent.setWebViewClient(new MyWebViewClient());
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
            if (messageDialog.isVisible()) {
                messageDialog.dismiss();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvContent.canGoBack()) {
            wvContent.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }
}
