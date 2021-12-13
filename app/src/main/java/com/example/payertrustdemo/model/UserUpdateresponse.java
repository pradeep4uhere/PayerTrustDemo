package com.example.payertrustdemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserUpdateresponse implements Serializable {

    public boolean success;
    public String message;
    public Data data;
    public class Data implements Serializable{
        @SerializedName("WalletBalance")
        public String walletBalance;
    }

}
