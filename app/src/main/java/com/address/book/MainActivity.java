package com.address.book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView mwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// OneSignal.SetLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);




        mwebview=(WebView)findViewById(R.id.webView);
        final String loadUrl=getString(R.string.url);

        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.getSettings().setUseWideViewPort(true);
        mwebview.getSettings().setLoadWithOverviewMode(true);
        mwebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mwebview.setScrollbarFadingEnabled(false);
        //mwebview.getSettings().setBuiltInZoomControls(true);
      // mwebview.getSettings().setSupportZoom(true);

        mwebview.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        try{
            mwebview.loadUrl(loadUrl);

        }catch (Exception e){
            e.printStackTrace();
        }

        this.mwebview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError (WebView view, int errorCode, String description, String failingUrl)
            {
                Log.i("onReceivedError", "onReceivedError: " + failingUrl + " errorCode: " + errorCode);
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.stopLoading();
                Intent loadSplash = new Intent(MainActivity.this,ErrorActivity.class);

                startActivity(loadSplash);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            }


        });
}


    @Override
    public void onBackPressed ()
    {
        if (mwebview.isFocused() && mwebview.canGoBack())
        {
            mwebview.goBack();
        }
        else
        {
            super.onBackPressed();
            finish();
        }
    }

}
