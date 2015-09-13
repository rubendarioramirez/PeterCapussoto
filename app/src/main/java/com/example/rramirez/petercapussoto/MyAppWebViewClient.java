package com.example.rramirez.petercapussoto;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.support.v4.app.ActivityCompat.startActivity;

//This class is meant to avoid WEBVIEW to load in external browser but inside the APP.
public class MyAppWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}