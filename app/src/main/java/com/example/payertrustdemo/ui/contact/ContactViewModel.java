package com.example.payertrustdemo.ui.contact;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payertrustdemo.LeftNavigation;
import com.example.payertrustdemo.Login;
import com.example.payertrustdemo.model.ContactResponse;
import com.example.payertrustdemo.model.LoginResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;
    private MutableLiveData<ContactResponse> contactResponse;

    public ContactViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("All Contacts (19)");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<ContactResponse> getContactList() {
        return contactResponse;
    }

    public void getAllContact(String userId) {

        Call<ContactResponse> call = RetrofitClient.getInstance().getMyApi().getAllContact(userId);
        call.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                ContactResponse temp = response.body();
                if(temp!= null){
                    if(temp.success){
                        contactResponse.setValue(temp);
                    }
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                contactResponse.setValue(null);
            }

        });
    }
}
