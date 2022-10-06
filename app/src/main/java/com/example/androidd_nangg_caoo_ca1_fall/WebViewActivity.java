package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=  findViewById(R.id.webBrowser);
        Intent intent = getIntent();
        if(intent!= null) {
            String link = intent.getStringExtra("link");
            webView.loadUrl(link);
        }
    }
}
