package com.example.payertrustdemo.ui.payment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.payertrustdemo.databinding.FragmentPaymentReportBinding;
import com.example.payertrustdemo.model.PaymentReportData;
import com.example.payertrustdemo.model.PaymentReportResponse;
import com.example.payertrustdemo.model.WalletData;
import com.example.payertrustdemo.model.WalletReportResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.ui.payment.PaymentReportFragment;
import com.example.payertrustdemo.ui.wallet.PaginationAdapter;
import com.example.payertrustdemo.ui.wallet.PaginationScrollListener;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentReportFragment extends Fragment {


    private PaymentReportViewModel paymentReportViewModel;
    private FragmentPaymentReportBinding binding;
    PaymentReportAdapter paginationAdapter;
    boolean isLoading = false;
    int currentPage = 1;
    boolean isLastPage = false;
    MyPreferences myPreferences;
    List<PaymentReportData> dataList;
    private Calendar calendar;
    private int day,month,year;
    String fromDate="",toDate="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentReportViewModel =
                new ViewModelProvider(this).get(PaymentReportViewModel.class);

        binding = FragmentPaymentReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        addFromDateListener();
        addToDateListener();

        myPreferences = new MyPreferences(getContext());
        dataList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        paginationAdapter = new PaymentReportAdapter(getActivity());
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        binding.recyclerview.setAdapter(paginationAdapter);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 1;
                dataList.clear();
                paginationAdapter.setDataList(dataList);
                loadFirstPage();
            }
        });

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

        return root;
    }


    public void loadFirstPage() {
        String offset  = String.valueOf(currentPage-1);
        Call<PaymentReportResponse> call = RetrofitClient.getInstance().getMyApi().
                paymentReport(myPreferences.getString(Constants.userId),offset,fromDate,toDate);
        call.enqueue(new Callback<PaymentReportResponse>() {
            @Override
            public void onResponse(Call<PaymentReportResponse> call, Response<PaymentReportResponse> response) {
                PaymentReportResponse temp = response.body();
                if(temp!= null){
                    if(temp.status){
                        binding.progressbar.setVisibility(View.GONE);
                        paginationAdapter.addAll(temp.data);

//                        if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
//                        else isLastPage = true;
                    }
                    //showToast(temp.message);
                }
                paginationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PaymentReportResponse> call, Throwable t) {
                binding.progressbar.setVisibility(View.GONE);
                t.printStackTrace();
            }

        });
    }

    public void loadNextPage() {
        String offset  = String.valueOf(currentPage * 20);
        Call<PaymentReportResponse> call = RetrofitClient.getInstance().getMyApi().paymentReport(myPreferences.getString(Constants.userId),offset,fromDate,toDate);
        call.enqueue(new Callback<PaymentReportResponse>() {
            @Override
            public void onResponse(Call<PaymentReportResponse> call, Response<PaymentReportResponse> response) {
                //paginationAdapter.removeLoadingFooter();
                isLoading = false;
                PaymentReportResponse temp = response.body();
                if(temp!= null){
                    if(temp.status){

                        paginationAdapter.addAll(temp.data);

//                        if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
//                        else isLastPage = true;
                    }
                    //showToast(temp.message);
                }
                paginationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PaymentReportResponse> call, Throwable t) {
                //showToast("Error, Try again");
            }

        });
    }

    public void addFromDateListener(){
        binding.edtFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        binding.edtFromDate.setText(dayOfMonth + " / "+ (monthOfYear+1) + " / " + year);
                        fromDate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                    }
                },year,month,day).show();
            }
        });
    }

    public void addToDateListener(){
        binding.edtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        binding.edtToDate.setText(dayOfMonth + " / "+ (monthOfYear+1) + " / " + year);
                        toDate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                    }
                },year,month,day).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
