package com.can.canutils.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.interfaces.OnPermissionListener;
import com.can.mvp.utils.ToastUtils;
import com.can.mvp.views.BindView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CAN on 2019/7/23.
 */
public class WebViewActivity extends BaseActivity {

    @BindView(id = R.id.view_web)
    private WebView mViewWeb;
    @BindView(id = R.id.progressBar)
    private ProgressBar mProgreeBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    public void initData(Bundle bundle) {

        mViewWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgreeBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        WebViewClient client = new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgreeBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgreeBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        };
        mViewWeb.setWebViewClient(client);
        mViewWeb.getSettings().setJavaScriptEnabled(true);
        mViewWeb.addJavascriptInterface(new Js(), "SendSmsInterface");
        String url = getIntent().getStringExtra("url");
        mViewWeb.loadUrl(url);
    }

    class Js {
        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void sendSms(String msg) {
            WebViewActivity.this.sendSms(msg);
        }
    }

    //发送短信
    private void sendSms(String msg) {
        try {
            Log.i("WebViewActivity", "msg : " + msg);
            JSONObject jsonObject = new JSONObject(msg);
            final String phone = jsonObject.getString("phone");
            final String content = jsonObject.getString("content");
            //监听短信发送状态的字符
            String sendSmsAction = "com.can.send_sms_action";
            //创建sendIntent
            Intent sendIntent = new Intent(sendSmsAction);
            final PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    0, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String result = "发送失败";
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            result = "发送成功";
                            break;
                        case android.telephony.SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            result = "发送失败";
                            break;
                        case android.telephony.SmsManager.RESULT_ERROR_RADIO_OFF:
                            result = "发送失败，无线处于关闭状态";
                            break;
                        case android.telephony.SmsManager.RESULT_ERROR_NULL_PDU:
                            result = "发送失败，没有指定的PDU";
                            break;
                        case android.telephony.SmsManager.RESULT_ERROR_NO_SERVICE:
                            result = "发送失败，无服务";
                            break;
                        default:
                            break;
                    }
                    ToastUtils.getInstance(getApplicationContext()).showText(result);
                    Log.i("WebViewActivity", "result = " + result);
                }
            }, new IntentFilter(sendSmsAction));
            if (checkReadPermission(Manifest.permission.SEND_SMS, REQUEST_SEND_SMS_PERMISSION, new OnPermissionListener() {
                @Override
                public void permissionListener(boolean hasPermission) {
                    if (hasPermission) {
                        //直接发送短信
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phone, null, content, pendingIntent, null);
                    } else {
                        ToastUtils.getInstance(getApplicationContext()).showText("暂无权限!");
                    }
                }
            })) {
                //直接发送短信
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, content, pendingIntent, null);
            }
        } catch (JSONException e) {
            Log.i("WebViewActivity", "e : " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewWeb.canGoBack())
            mViewWeb.goBack();
        else {
            super.onBackPressed();
        }
    }
}
