package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payertrustdemo.ui.contact.ContactFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ContactDetail extends AppCompatActivity {
    public FloatingActionButton floatingActionButton;
    Dialog dialog;
    Button button;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        //Get Back To Contact List Screen
        ImageView imageView = (ImageView) findViewById(R.id.btn_from_conatct_details);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Contact Details List");
                Intent intent = new Intent(ContactDetail.this, ContactFragment.class);
                startActivity(intent);
            }
        });

        TextView textView = (TextView) findViewById(R.id.add_bank_account);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Adding New Account");
                Intent intent = new Intent(ContactDetail.this, AddAccount.class);
                startActivity(intent);
            }
        });

        //Move Screen to Transfer amount
        CardView cardView = (CardView) findViewById(R.id.account_item);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Move to Transfer Money");
                Intent intent = new Intent(ContactDetail.this, TransferMoney.class);
                startActivity(intent);
            }
        });
    }


    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}