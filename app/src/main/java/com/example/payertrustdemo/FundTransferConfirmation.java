package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FundTransferConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_transfer_confirmation);
        Button button = (Button)findViewById(R.id.money_transfer_submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Back To Money Transfer");
                Intent intent = new Intent(FundTransferConfirmation.this, MoneyTransferSuccess.class);
                startActivity(intent);
            }
        });


        ImageView imageView = (ImageView) findViewById(R.id.back_to_fund_transfer_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Back To Money Transfer");
                Intent intent = new Intent(FundTransferConfirmation.this, TransferMoney.class);
                startActivity(intent);
            }
        });
    }


    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}