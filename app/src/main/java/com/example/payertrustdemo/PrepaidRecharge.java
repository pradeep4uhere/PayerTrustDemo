package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.payertrustdemo.ui.home.HomeFragment;

public class PrepaidRecharge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepaid_recharge);
        ImageView imageView =(ImageView) findViewById(R.id.back_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrepaidRecharge.this, HomeFragment.class);
                //setContentView(R.layout.activity_main);
                startActivity(intent);
            }
        });

    }
}