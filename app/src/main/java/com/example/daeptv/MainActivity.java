package com.example.daeptv;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        WebSettings ws = webView.getSettings();

        // ✔ WAJIB untuk sql.js & WASM
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);

        // ✔ WAJIB untuk loading .wasm dari /assets/
        ws.setAllowFileAccess(true);
        ws.setAllowContentAccess(true);

        // ✔ WAJIB untuk WASM & AJAX dari file:///android_asset/
        ws.setAllowFileAccessFromFileURLs(true);
        ws.setAllowUniversalAccessFromFileURLs(true);

        // Opsional tapi baik untuk SPA
        webView.setWebViewClient(new WebViewClient());

        // Load index.html dari assets
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Pastikan WebView back press tidak langsung keluar
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
