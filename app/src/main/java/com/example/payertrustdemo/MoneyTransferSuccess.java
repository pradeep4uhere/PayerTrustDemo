package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.FundTransferResponse;

public class MoneyTransferSuccess extends AppCompatActivity {

    TextView txtBankName,txtAccount,txtIfsc,txtRefNo,txtName,txtPaymentMode,txtPaymentStatus,
    txtAmount,txtDate;
    FundTransferResponse fundTransferResponse;
    String bankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer_success);
        fundTransferResponse = (FundTransferResponse) getIntent().getSerializableExtra("transferResponse");
        bankName = getIntent().getStringExtra("bankName");
        txtBankName = findViewById(R.id.txtBankName);
        txtAccount = findViewById(R.id.txtAccountNo);
        txtIfsc = findViewById(R.id.txtIFSC);
        txtRefNo = findViewById(R.id.txtRefNo);
        txtName = findViewById(R.id.txtName);
        txtPaymentMode = findViewById(R.id.txtPaymentMode);
        txtPaymentStatus = findViewById(R.id.txtPaymentStatus);
        txtAmount = findViewById(R.id.txtAmount);
        txtDate = findViewById(R.id.txtDate);
        //Get Back To Contact List Screen
        ImageView imageView = (ImageView) findViewById(R.id.back_to_fund_transfer_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();

    }

    public void initView(){
        txtBankName.setText(bankName);
        txtName.setText(fundTransferResponse.dataSet.bank.name);
        txtAccount.setText("Acc No "+fundTransferResponse.dataSet.bank.account_number);
        txtIfsc.setText("IFSC Code "+fundTransferResponse.dataSet.bank.ifsc_code);
        txtPaymentMode.setText(fundTransferResponse.dataSet.mode);
        txtAmount.setText("Rs. "+fundTransferResponse.dataSet.amount);
        txtPaymentStatus.setText(fundTransferResponse.dataSet.status);
        txtRefNo.setText("Ref No "+fundTransferResponse.dataSet.RefNo);
        txtDate.setText(fundTransferResponse.dataSet.PaymentDate);
    }

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}