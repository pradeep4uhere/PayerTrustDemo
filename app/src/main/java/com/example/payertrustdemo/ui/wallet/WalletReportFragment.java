package com.example.payertrustdemo.ui.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payertrustdemo.R;
import com.example.payertrustdemo.Wallet;
import com.example.payertrustdemo.WalletViewAdapter;
import com.example.payertrustdemo.databinding.FragmentMobilePinBinding;
import com.example.payertrustdemo.databinding.FragmentWalletReportBinding;
import com.example.payertrustdemo.model.CreateContactResponse;
import com.example.payertrustdemo.model.WalletData;
import com.example.payertrustdemo.model.WalletReportResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.ui.mpin.MobilePinViewModel;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletReportFragment extends Fragment {

    private WalletReportViewModel walletReportViewModel;
    private FragmentWalletReportBinding binding;
    PaginationAdapter paginationAdapter;
    boolean isLoading = false;
    int currentPage = 1;
    boolean isLastPage = false;
    MyPreferences myPreferences;
    List<WalletData> dataList;

    public  WalletReportFragment(){

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        walletReportViewModel =
                new ViewModelProvider(this).get(WalletReportViewModel.class);

        binding = FragmentWalletReportBinding.inflate(inflater, container, false);
        myPreferences = new MyPreferences(getContext());
        dataList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        paginationAdapter = new PaginationAdapter(getActivity());
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        binding.recyclerview.setAdapter(paginationAdapter);

        binding.recyclerview.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();

        View root = binding.getRoot();

        return root;
    }


    public void loadFirstPage() {
        String offset  = String.valueOf(currentPage-1);
        Call<WalletReportResponse> call = RetrofitClient.getInstance().getMyApi().walletReport(myPreferences.getString(Constants.userId),offset);
        call.enqueue(new Callback<WalletReportResponse>() {
            @Override
            public void onResponse(Call<WalletReportResponse> call, Response<WalletReportResponse> response) {
                WalletReportResponse temp = response.body();
                if(temp!= null){
                    if(temp.status){
                        binding.progressbar.setVisibility(View.GONE);
                        paginationAdapter.addAll(temp.data);

//                        if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
//                        else isLastPage = true;
                    }
                    //showToast(temp.message);
                }
            }

            @Override
            public void onFailure(Call<WalletReportResponse> call, Throwable t) {
                //showToast("Error, Try again");
            }

        });
    }

    public void loadNextPage() {
        String offset  = String.valueOf(currentPage * 20);
        Call<WalletReportResponse> call = RetrofitClient.getInstance().getMyApi().walletReport(myPreferences.getString(Constants.userId),offset);
        call.enqueue(new Callback<WalletReportResponse>() {
            @Override
            public void onResponse(Call<WalletReportResponse> call, Response<WalletReportResponse> response) {
                paginationAdapter.removeLoadingFooter();
                isLoading = false;
                WalletReportResponse temp = response.body();
                if(temp!= null){
                    if(temp.status){

                        paginationAdapter.addAll(temp.data);

//                        if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
//                        else isLastPage = true;
                    }
                    //showToast(temp.message);
                }
            }

            @Override
            public void onFailure(Call<WalletReportResponse> call, Throwable t) {
                //showToast("Error, Try again");
            }

        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
