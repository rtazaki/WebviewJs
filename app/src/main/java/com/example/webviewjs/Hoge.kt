package com.example.webviewjs

import android.webkit.JavascriptInterface
import android.webkit.WebView


class Hoge(private val webView: WebView) {
    @JavascriptInterface
    fun funchoge() {
        webView.post {
            webView.loadUrl("file:///android_asset/hoge_after.html")
        }
    }
}