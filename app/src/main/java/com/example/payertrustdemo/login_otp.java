package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_otp extends AppCompatActivity {
    Button callSignUp, dashboardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);

        dashboardBtn = findViewById(R.id.procced_btn);
        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_otp.this, LeftNavigation.class);
                startActivity(intent);
            }
        });
    }
}