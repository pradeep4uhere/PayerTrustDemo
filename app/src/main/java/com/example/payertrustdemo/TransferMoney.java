package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TransferMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);

        //Move to Transfer Money Confirmation Screen
        Button button = (Button) findViewById(R.id.transfer_money_submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Move to transfer money");
                Intent intent = new Intent(TransferMoney.this, FundTransferConfirmation.class);
                startActivity(intent);
            }
        });

    }



    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}