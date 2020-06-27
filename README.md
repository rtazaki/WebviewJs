# WebviewJs
Androidでwebviewを使ってローカルのhtmlを表示するサンプル.

## やったこと
 - activity_main.xmlのTextViewをWebViewにして、idを足した。(android:id="@+id/webView")
 - ローカルのHTMLとjavascriptを動かすだけなので、AndroidManifest.xmlに \
 `<uses-permission android:name="android.permission.INTERNET"/>`は不要。→結果Manifestに変更なし。
 - appで右クリック→新規→フォルダー→Assetsフォルダーでターゲットはmainのまま完了する。

## MainActivity
```
    @SuppressLint("SetJavaScriptEnabled")
...
        webView.settings.javaScriptEnabled = true
```
@SuppressLint("SetJavaScriptEnabled")は、javaScriptEnabledしたときに警告メッセージに従うと
勝手に付く。javascriptは世間的には使わない方向になるけど、それでwarning出てもつまらないので、
この警告を無視する。
```
//        webView.loadUrl("file:///android_asset/index.html")
//        webView.loadUrl("file:///android_asset/index2.html")
//        webView.addJavascriptInterface(WebAppInterface(this), "Android")
```
index.htmlもindex2.html(+example.js)も、[公式][1]に従って作成。違いはjsを分離するかしないか。
WebAppInterfaceは公式の例のまま。ToastをJSからキックするために、this→contextとして渡し、
Androidという名前でjs側から呼び出せるようにしている。

## WebAppInterface
公式のまま。
やっていること: showToastがコールされたら、Toastで'Hello Android!'を表示。

## assetsフォルダー
- index.html: 公式のまま
- index2.html: <script></script>をsrc="example.js"として外に分離。
やっていること: Say helloボタン押下でshowAndroidToast('Hello Android!')
→javascript→Android.showToast(toast);

## MainActivity hoge
```
        webView.loadUrl("file:///android_asset/hoge.html")
        webView.addJavascriptInterface(Hoge(webView), "hoge3")
```
- hoge.html + hoge.js(index2と同じく、jsは分離する)
- contextではなく、webViewを引き渡し。

## Hoge
- webView.loadUrl("file:///android_asset/hoge_after.html")をそのまま呼ぶと、
怒られるので、[webView.post{}][2]の中でloadUrlするといい感じになる。

## assetsフォルダー
- hoge.html
- hoge.js

setTimeoutを使って、3秒後にAndroid側に処理を渡す。
- hoge_after.html
上書きしたいhtmlファイル。

[1]:https://developer.android.com/guide/webapps/webview?hl=ja#UsingJavaScript
[2]:https://stackoverrun.com/ja/q/6186742
