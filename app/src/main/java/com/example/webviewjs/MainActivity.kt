package com.example.webviewjs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView.settings.javaScriptEnabled = true
//        webView.loadUrl("file:///android_asset/index.html")
//        webView.loadUrl("file:///android_asset/index2.html")
//        webView.addJavascriptInterface(WebAppInterface(this), "Android")
//        webView.loadUrl("file:///android_asset/hoge.html")
//        webView.addJavascriptInterface(Hoge(webView), "hoge3")
        // https://www.pixelimage.jp/blog/2012/04/loading_icon.html
        webView.loadUrl("file:///android_asset/loading.html")
        webView.addJavascriptInterface(Loading(webView), "Android")
    }
}