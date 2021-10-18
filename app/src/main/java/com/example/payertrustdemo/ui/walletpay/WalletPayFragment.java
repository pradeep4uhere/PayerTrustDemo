package com.example.payertrustdemo.ui.walletpay;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.payertrustdemo.R;
import com.example.payertrustdemo.databinding.FragmentContactBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WalletPayFragment extends Fragment {


    private static final String AGR_PARAM1 = "param1";
    private static final String AGR_PARAM2 = "param2";

    public FloatingActionButton floatingActionButton;


    private String mParam1;
    private String mParam2;

    private WalletPayViewModel walletpayViewModel;
    private FragmentContactBinding binding;

    Button button;
    Dialog dialog;

    public  WalletPayFragment(){

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_wallet_pay,container,false);

        Button button = (Button) view.findViewById(R.id.wallet_pay_submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getParentFragment().getContext(), WalletPaySuccess.class);
                startActivity(intent);
            }
        });
        return  view;

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
