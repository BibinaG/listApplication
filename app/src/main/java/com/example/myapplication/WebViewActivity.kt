package com.example.myapplication

import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {
    var pdfUrl: String = ""
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initiews()


    }

    private fun initiews() {
//        val webView = findViewById<WebView>(R.id.webview)
//        webView.settings.javaScriptEnabled = true
//        webView.settings.allowFileAccessFromFileURLs = true
//        webView.settings.allowUniversalAccessFromFileURLs = true
//        webView.webChromeClient = WebChromeClient()


        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.allowFileAccessFromFileURLs = true
        binding.webview.settings.allowUniversalAccessFromFileURLs = true
        binding.webview.webChromeClient = WebChromeClient()

//        pdfUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfUrl"
        pdfUrl = "https://docs.google.com/gview?embedded=true&url=\" + \"https://app.pos.bharuwa.com:8030/aquilitePdf/Aqualite_hawai.pdf"
        binding.webview.loadUrl(pdfUrl)

    }
}