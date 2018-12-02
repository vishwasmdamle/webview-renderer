package com.getsimpl.app.webviewrenderer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LogsActivity extends AppCompatActivity {

    private String browserLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        browserLogs = getIntent().getStringExtra("logs");
        setTitle("Browser Logs");
        TextView logTextView = findViewById(R.id.logTextView);
        logTextView.setText(browserLogs);
        logTextView.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.copyLogs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyLogsToClipboard();
            }
        });
   }

    private void copyLogsToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("BrowserLogs", browserLogs);
        if (clipboard == null) {
            Toast.makeText(getApplicationContext(), "Clipboard not available", Toast.LENGTH_LONG).show();
        } else {
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }


}
