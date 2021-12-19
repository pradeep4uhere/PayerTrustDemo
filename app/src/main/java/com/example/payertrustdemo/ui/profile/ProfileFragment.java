package com.example.payertrustdemo.ui.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.payertrustdemo.AddAccount;
import com.example.payertrustdemo.R;
import com.example.payertrustdemo.WalletPaySuccess;
import com.example.payertrustdemo.databinding.FragmentProfileBinding;
import com.example.payertrustdemo.model.BankListResponse;
import com.example.payertrustdemo.model.CityListResponse;
import com.example.payertrustdemo.model.GetUserUpdateResponse;
import com.example.payertrustdemo.model.StateListResponse;
import com.example.payertrustdemo.model.WalletTransferResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    MyPreferences myPreferences;
    GetUserUpdateResponse getUserUpdateResponse;
    String selectedState,stateId;
    String selectedCity,cityId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        myPreferences = new MyPreferences(getContext());

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.saveKycBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address1 = binding.addressLine1.getText().toString().trim();
                String address2 = binding.addressLine2.getText().toString().trim();
                String pincode = binding.pincode.getText().toString().trim();
                String dob = binding.dateOfBirth.getText().toString().trim();
                String adhar = binding.aadharCardInput.getText().toString().trim();
                String pancard = binding.panCardInput.getText().toString().trim();
                String gst = binding.gstNumberInput.getText().toString().trim();
                if(TextUtils.isEmpty(address1)){
                    showToast("Enter address line 1");
                    return;
                }
                else if(TextUtils.isEmpty(address2)){
                    showToast("Enter address line 2");
                    return;
                }
                else if(TextUtils.isEmpty(selectedState)){
                    showToast("Enter state");
                    return;
                }
                else if(TextUtils.isEmpty(selectedCity)){
                    showToast("Enter city");
                    return;
                }
                else if(TextUtils.isEmpty(pincode)){
                    showToast("Enter pin code");
                    return;
                }
                else if(TextUtils.isEmpty(dob)){
                    showToast("Enter date of birth");
                    return;
                }
                else if(TextUtils.isEmpty(adhar)){
                    showToast("Enter Adhar number");
                    return;
                }
                else if(TextUtils.isEmpty(pancard)){
                    showToast("Enter PAN card number");
                    return;
                }
                else if(TextUtils.isEmpty(gst)){
                    showToast("Enter GST number");
                    return;
                }
                else {
                    updateUserDetailas(address1,address2,stateId,cityId,pincode,dob,pancard,adhar,gst);
                }
            }
        });
        getUserUpdate();
        getState();
        return root;
    }

    private void getUserUpdate() {
        showPopupProgressSpinner(true,getContext());
        String userId = myPreferences.getString(Constants.userId);
        Call<GetUserUpdateResponse> call = RetrofitClient.getInstance().getMyApi().getUserUpdate(userId);
        call.enqueue(new Callback<GetUserUpdateResponse>() {
            @Override
            public void onResponse(Call<GetUserUpdateResponse> call, Response<GetUserUpdateResponse> response) {
                showPopupProgressSpinner(false,getContext());
                getUserUpdateResponse = response.body();

                if(getUserUpdateResponse!= null){
                    if(getUserUpdateResponse.success){
                        updateUI();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserUpdateResponse> call, Throwable t) {
                showPopupProgressSpinner(false,getContext());
            }

        });
    }

    private void getState() {
        Call<StateListResponse> call = RetrofitClient.getInstance().getMyApi().getStateList();
        call.enqueue(new Callback<StateListResponse>() {
            @Override
            public void onResponse(Call<StateListResponse> call, Response<StateListResponse> response) {
                StateListResponse stateListResponse = response.body();

                if(stateListResponse!= null){
                    if(stateListResponse.success){
                        initStateAutoList(stateListResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<StateListResponse> call, Throwable t) {
            }

        });
    }


    private void getCity(String stateId) {
        showPopupProgressSpinner(true,getContext());
        String userId = myPreferences.getString(Constants.userId);
        Call<CityListResponse> call = RetrofitClient.getInstance().getMyApi().getCityList(stateId);
        call.enqueue(new Callback<CityListResponse>() {
            @Override
            public void onResponse(Call<CityListResponse> call, Response<CityListResponse> response) {
                showPopupProgressSpinner(false,getContext());
                CityListResponse cityListResponse = response.body();

                if(cityListResponse!= null){
                    if(cityListResponse.success){
                        initCityAutoList(cityListResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<CityListResponse> call, Throwable t) {
                showPopupProgressSpinner(false,getContext());
            }

        });
    }

    private void initStateAutoList(StateListResponse stateListResponse)
    {
        //Create adapter
        ArrayAdapter adapter = new ArrayAdapter<StateListResponse.Datum>(getContext(), android.R.layout.simple_spinner_item, stateListResponse.data);

        //Set adapter
        binding.aoutoCompleteState.setAdapter(adapter);
        binding.aoutoCompleteState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                selectedState = ((StateListResponse.Datum)adapter.getItem(pos)).state_name;
                stateId = String.valueOf(((StateListResponse.Datum)adapter.getItem(pos)).id);

                getCity(stateId);

            }
        });
    }

    private void initCityAutoList(CityListResponse cityListResponse)
    {
        //Create adapter
        ArrayAdapter adapter = new ArrayAdapter<CityListResponse.Datum>(getContext(), android.R.layout.simple_spinner_item, cityListResponse.data);
        //Set adapter
        binding.aoutoCompleteCity.setAdapter(adapter);
        binding.aoutoCompleteCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                selectedCity = ((CityListResponse.Datum)adapter.getItem(pos)).city_name;
                cityId = String.valueOf(((CityListResponse.Datum)adapter.getItem(pos)).id);

            }
        });
    }

    public void updateUI(){
        binding.txtNmae.setText(getUserUpdateResponse.data.userProfile.name);
        binding.txtMobile.setText("Mobile: "+getUserUpdateResponse.data.userProfile.mobile);
        binding.txtEmail.setText("Email: "+getUserUpdateResponse.data.userProfile.emailAddress);
        binding.txtAgentCode.setText(getUserUpdateResponse.data.userProfile.agentCode);
        binding.txtKycActive.setText(getUserUpdateResponse.data.userProfile.kYC_Status);

        if(getUserUpdateResponse.data.userProfile.kYC_Status.equalsIgnoreCase("Active")){
            binding.linLayAddress.setVisibility(View.VISIBLE);
            binding.linLayAddressEdit.setVisibility(View.GONE);
            binding.linLayKYC.setVisibility(View.VISIBLE);
            binding.linLayKYCEdit.setVisibility(View.GONE);

            binding.txtAddress1.setText(getUserUpdateResponse.data.kYC.address1);
            binding.txtAddress2.setText(getUserUpdateResponse.data.kYC.address2);
            binding.txtPinCode.setText(getUserUpdateResponse.data.kYC.district+", "+
                    getUserUpdateResponse.data.kYC.pincode);

            binding.txtDOB.setText(getUserUpdateResponse.data.kYC.dateOfbirth);
            binding.txtAdharCard.setText(getUserUpdateResponse.data.kYC.aadhar_card);
            binding.txtPanCard.setText(getUserUpdateResponse.data.kYC.pancard);
            binding.txtGSTNo.setText(getUserUpdateResponse.data.kYC.gST_number);
        }
        else{
            binding.linLayAddress.setVisibility(View.GONE);
            binding.linLayAddressEdit.setVisibility(View.VISIBLE);
            binding.linLayKYC.setVisibility(View.GONE);
            binding.linLayKYCEdit.setVisibility(View.VISIBLE);

            binding.addressLine1.setText(getUserUpdateResponse.data.kYC.address1);
            binding.addressLine2.setText(getUserUpdateResponse.data.kYC.address2);
            binding.txtPinCode.setText(getUserUpdateResponse.data.kYC.district+", "+
                    getUserUpdateResponse.data.kYC.pincode);

        }
    }

    private void updateUserDetailas(String address_1,
                                    String address_2,
                                    String state_id,
                                    String city_id,
                                    String pincode,
                                    String date_of_birth,
                                    String pan_card_number,
                                    String aadhar_card_number,
                                    String gst_number) {
        showPopupProgressSpinner(true,getContext());
        String userId = myPreferences.getString(Constants.userId);
        Call<GetUserUpdateResponse> call = RetrofitClient.getInstance().getMyApi().userDetailsUpdate(userId,address_1,address_2,state_id,city_id,pincode,date_of_birth,pan_card_number,aadhar_card_number,gst_number);
        call.enqueue(new Callback<GetUserUpdateResponse>() {
            @Override
            public void onResponse(Call<GetUserUpdateResponse> call, Response<GetUserUpdateResponse> response) {
                showPopupProgressSpinner(false,getContext());
                getUserUpdateResponse = response.body();

                if(getUserUpdateResponse!= null){
                    if(getUserUpdateResponse.success){
                        updateUI();
                    }
                    showToast(getUserUpdateResponse.message);
                }
            }

            @Override
            public void onFailure(Call<GetUserUpdateResponse> call, Throwable t) {
                showPopupProgressSpinner(false,getContext());
            }

        });
    }


    private Dialog progressDialog = null;
    private ProgressBar progressBar;

    public void showPopupProgressSpinner(Boolean isShowing, Context context) {
        if (isShowing == true) {
            progressDialog = new Dialog(context);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.popup_progressbar);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));

            progressBar = (ProgressBar) progressDialog
                    .findViewById(R.id.progressBar);
            progressDialog.show();
        } else if (isShowing == false) {
            progressDialog.dismiss();
        }
    }

    private void showToast(String message)
    {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}