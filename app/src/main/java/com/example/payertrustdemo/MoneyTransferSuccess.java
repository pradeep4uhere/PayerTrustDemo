package com.example.payertrustdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.payertrustdemo.ui.contact.ContactFragment;
import com.example.payertrustdemo.ui.home.HomeFragment;
import com.example.payertrustdemo.ui.payment.PaymentReportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MoneyTransferSuccess extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle toggle;
//    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer_success);

//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navigationView = (NavigationView) findViewById(R.id.nav_home);
//
//        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open,R.string.Close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        navigationView.setNavigationItemSelectedListener(this);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_money_success);
        //bottomNavigationView.setSelectedItemId(R.id.bottom_home_btn);
        //bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                switch (item.getItemId()){
//                    case R.id.bottom_home_btn:
//                        startActivity(new Intent(getApplicationContext()
//                                ,LeftNavigation.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                    case R.id.bottom_pay_again_btn:
//                         startActivity(new Intent(getApplicationContext()
//                        ,TransferMoney.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                    case R.id.bottom_report_btn:
//                     startActivity(new Intent(getApplicationContext()
//                    , PaymentReportFragment.class));
//                    overridePendingTransition(0,0);
//                    return true;
//                }
//                return false;
//            }
        //});

    }


    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}