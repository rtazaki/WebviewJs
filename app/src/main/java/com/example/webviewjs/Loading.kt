package com.example.webviewjs

import android.webkit.JavascriptInterface
import android.webkit.WebView


class Loading(private val webView: WebView) {
    @JavascriptInterface
    fun exec() {
        webView.post {
            webView.loadUrl("file:///android_asset/hoge_after.html")
        }
    }
}