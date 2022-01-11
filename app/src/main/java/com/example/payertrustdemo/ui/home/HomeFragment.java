package com.example.payertrustdemo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.payertrustdemo.PrepaidRecharge;
import com.example.payertrustdemo.R;
import com.example.payertrustdemo.databinding.FragmentHomeBinding;
import com.example.payertrustdemo.model.GetUserUpdateResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.ui.wallet.WalletReportFragment;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Button prepaidRechargeBtn;
    MyPreferences myPreferences;
    CardView cardView, card1, card2, card3, card4, card5, card6, card7, card8, card9;
    TextView txtBalance,txtName, textbtn;
    public  HomeFragment(){

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        myPreferences = new MyPreferences(getActivity());
        card1 = (CardView) view.findViewById(R.id.card1);
        card8 = (CardView) view.findViewById(R.id.card8);
        txtBalance = view.findViewById(R.id.balance);
        txtName = view.findViewById(R.id.name_title);
        txtBalance.setText(myPreferences.getString(Constants.walletBalance));
        txtName.setText("Hi "+myPreferences.getString(Constants.firstName) + " "
                + myPreferences.getString(Constants.lastName));
        card1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i;
            i = new Intent(getContext(),PrepaidRecharge.class);
            startActivity(i);
            }
        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* WalletReportFragment nextFrag= new WalletReportFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();*/
            }
        });



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getUserUpdate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getUserUpdate() {
        String userId = myPreferences.getString(Constants.userId);
        Call<GetUserUpdateResponse> call = RetrofitClient.getInstance().getMyApi().getUserUpdate(userId);
        call.enqueue(new Callback<GetUserUpdateResponse>() {
            @Override
            public void onResponse(Call<GetUserUpdateResponse> call, Response<GetUserUpdateResponse> response) {
                GetUserUpdateResponse userUpdateresponse = response.body();

                if(userUpdateresponse!= null){
                    if(userUpdateresponse.success){
                        txtBalance.setText(userUpdateresponse.data.walletBalance);
                        myPreferences.saveString(Constants.walletBalance,userUpdateresponse.data.walletBalance);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserUpdateResponse> call, Throwable t) {

            }

        });
    }

}

// card1 = (CardView) view.findViewById(R.id.card1);
//         card2 = (CardView) view.findViewById(R.id.card8);
//         card1.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Intent i;
//        switch (v.getId()){
//        case R.id.card8:
//        i = new Intent(getContext(),PrepaidRecharge.class);
//        startActivity(i);
//        break;
//
//        case R.id.card1:
//        i = new Intent(getContext(), PaymentReportFragment.class);
//        startActivity(i);
//        break;
//        }
//        }
//        });