package com.example.payertrustdemo.ui.recharge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.payertrustdemo.R;
import com.example.payertrustdemo.databinding.ActivityPaymentDetailsBinding;
import com.example.payertrustdemo.databinding.ActivityPaymentLinkBinding;

public class PaymentLinkActivity extends AppCompatActivity {

    ActivityPaymentLinkBinding binding;
    String link;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_payment_link);
        link = getIntent().getStringExtra("link");
        binding = ActivityPaymentLinkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webView.loadUrl(link);
        binding.webView.setWebViewClient(new MyBrowser());
        //menuItem = (MenuItem) findViewById(R.id.bottom_home);
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}