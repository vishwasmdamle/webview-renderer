package com.getsimpl.app.webviewrenderer.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.getsimpl.app.webviewrenderer.LogsActivity;
import com.getsimpl.app.webviewrenderer.R;

public class WebviewActivity extends AppCompatActivity {

    private static final String TAG = WebviewActivity.class.getName();
    private String url;
    private StringBuilder browserLog = new StringBuilder();
    private boolean jsEnabled;
    private boolean domStorageEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
//        setActionBarProperties();
        url = getIntent().getStringExtra("url");
        jsEnabled = getIntent().getBooleanExtra("jsEnabled", false);
        domStorageEnabled = getIntent().getBooleanExtra("domStorageEnabled", false);
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogs();
            }
        });
        openWebView();
    }

    private void setActionBarProperties() {
        if (getSupportActionBar() == null)
        return;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Webview");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void openLogs() {
        Intent intent = new Intent(getApplicationContext(), LogsActivity.class);
        intent.putExtra("logs", browserLog.toString());
        startActivity(intent);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openWebView() {
        WebView mWebView = findViewById(R.id.webView1);
        mWebView.setVerticalScrollBarEnabled(true);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(jsEnabled);
        settings.setDomStorageEnabled(domStorageEnabled);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i(TAG, "BrowserLog: " + consoleMessage.message());
                browserLog.append(consoleMessage.message()).append('\n');
                return false;
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.d(TAG, "Loading " + url);
            }
        });
        mWebView.loadUrl(url);
    }
}
