package com.example.payertrustdemo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.payertrustdemo.HelpActivity;
import com.example.payertrustdemo.PrepaidRecharge;
import com.example.payertrustdemo.R;
import com.example.payertrustdemo.WebLinkActivity;
import com.example.payertrustdemo.databinding.FragmentHomeBinding;
import com.example.payertrustdemo.model.GetUserUpdateResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.ui.contact.ContactFragment;
import com.example.payertrustdemo.ui.payment.PaymentReportFragment;
import com.example.payertrustdemo.ui.recharge.PaymentLinkActivity;
import com.example.payertrustdemo.ui.recharge.RechargeFragment;
import com.example.payertrustdemo.ui.wallet.WalletReportFragment;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Button prepaidRechargeBtn;
    MyPreferences myPreferences;
    CardView cardView, card1, card2, card3, card4, card5, card6, card7, card8, card9,card10,card11,card12;
    TextView txtBalance,txtName, textBtn, textBtneqquest;
    public  HomeFragment(){

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        myPreferences = new MyPreferences(getActivity());
        card1 = (CardView) view.findViewById(R.id.card1);
        card2 = (CardView) view.findViewById(R.id.card2);
        card3 = (CardView) view.findViewById(R.id.card3);
        card4 = (CardView) view.findViewById(R.id.card4);
        card5 = (CardView) view.findViewById(R.id.card5);
        card6 = (CardView) view.findViewById(R.id.card6);
        card7 = (CardView) view.findViewById(R.id.card7);
        card8 = (CardView) view.findViewById(R.id.card8);
        card9 = (CardView) view.findViewById(R.id.card9);
        card10 = (CardView) view.findViewById(R.id.card10);
        card11 = (CardView) view.findViewById(R.id.card11);
        card12 = (CardView) view.findViewById(R.id.card12);
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

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://www.payertrust.in/electricity");
                intent.putExtra("title",  "Electricity");
                getContext().startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://www.payertrust.in/gas");
                intent.putExtra("title",  "Gas");
                getContext().startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://payertrust.in/dth");
                intent.putExtra("title",  "DTH");
                getContext().startActivity(intent);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://www.payertrust.in/water");
                intent.putExtra("title",  "Water");
                getContext().startActivity(intent);
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://www.payertrust.in/landline");
                intent.putExtra("title",  "Card");
                getContext().startActivity(intent);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://www.payertrust.in/payrent");
                intent.putExtra("title",  "Pay Rent");
                getContext().startActivity(intent);
            }
        });

        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebLinkActivity.class);
                intent.putExtra("link",  "https://www.payertrust.in/faqs");
                intent.putExtra("title",  "FAQ");
                getContext().startActivity(intent);
            }
        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentReportFragment nextFrag= new PaymentReportFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_left_navigation, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WalletReportFragment nextFrag= new WalletReportFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_left_navigation, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        //BottomSheet For Request Money
        textBtneqquest = (TextView) view.findViewById(R.id.request_moneny);
        textBtneqquest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_request_money);
                Button btnSubmit = bottomSheetDialog.findViewById(R.id.btnSubmit);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.show();

            }
        });

        textBtn = (TextView) view.findViewById(R.id.add_moneny_btn);
        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RechargeFragment nextFrag= new RechargeFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_left_navigation, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                //Intent intent = new Intent(getActivity(), RechargeFragment.class);
                //startActivity(intent);
//                Intent i;
//                i = new Intent(getContext(), RechargeFragment.class);
//                startActivity(i);
            }
        });

        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getContext(), HelpActivity.class);
                startActivity(i);
            }
        });

        card12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HelpActivity.class);
                startActivity(i);
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
