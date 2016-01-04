package com.example.student.steckerlager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.lang.reflect.Method;

public class SteckerLager extends AppCompatActivity {

    private final static String URL_TO_LOAD = "http://192.168.42.244";
    WebView webView;
    int api_level = Build.VERSION.SDK_INT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout_stecker);


        setWebView(URL_TO_LOAD);





    }

    public void setWebView(String Server_IP) {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if(api_level >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        }
        webView.getSettings().setDomStorageEnabled(true);

        loadResource(webView, Server_IP);

        webView.setWebViewClient(new WebViewClient() {
            //without webClient the link would redirect to a mobile browser
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        webView.saveState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return button returns to the previous site if possible
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    private void loadResource(WebView wv, String resource) {
        wv.loadUrl(resource);
        wv.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stecker_lager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
