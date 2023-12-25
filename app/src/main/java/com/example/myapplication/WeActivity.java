package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WeActivity extends AppCompatActivity {
    String pdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.setWebChromeClient(new WebChromeClient());

        String pdfUrl = "https://docs.google.com/gview?embedded=true&url=" + "https://app.pos.bharuwa.com:8030/aquilitePdf/Aqualite_hawai.pdf";
        webView.loadUrl(pdfUrl);



//        pdfUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url" + pdfUrl;
//        webView.loadUrl(pdfUrl);
    }
}