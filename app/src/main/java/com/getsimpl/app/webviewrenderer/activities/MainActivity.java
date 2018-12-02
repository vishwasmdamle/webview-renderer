package com.getsimpl.app.webviewrenderer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.getsimpl.app.webviewrenderer.R;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        setTitle("Webview Renderer");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebviewActivity.class);
                String url = ((TextView)findViewById(R.id.urlEditText)).getText().toString();


                intent.putExtra("url", url);
                intent.putExtra("jsEnabled", ((CheckBox)findViewById(R.id.jsEnabledCheckbox)).isChecked());
                intent.putExtra("domStorageEnabled", ((CheckBox)findViewById(R.id.domStorageEnabledCheckbox)).isChecked());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
