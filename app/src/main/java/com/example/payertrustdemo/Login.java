package com.example.payertrustdemo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.payertrustdemo.model.LoginRequest;
import com.example.payertrustdemo.model.LoginResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button callSignUp, dashboardBtn, forgotPasswdBtn;
    private LoginResponse loginResponse;
    EditText etNumber,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        callSignUp = findViewById(R.id.login_back_from_success);
        etNumber = findViewById(R.id.etNumber);
        etPassword = findViewById(R.id.etPassword);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        dashboardBtn = findViewById(R.id.reset_btn_submit);
        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNumber.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(Login.this,"Please enter number",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    new HTTPReqTask().execute();
                    //loginApi(number,password);
                }
            }
        });


        forgotPasswdBtn = findViewById(R.id.fotgot_link_btn);
        forgotPasswdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void loginApi(String mobile, String password) {

        JSONObject object = new JSONObject();
        try {
            object.put("mobile","9876543210");
            object.put("password","pradeep");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<LoginResponse> call = RetrofitClient.getInstance().getMyApi().login(object.toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                if(loginResponse!= null){
                    if(loginResponse.success){
                        MyPreferences myPreferences = new MyPreferences(Login.this);
                        myPreferences.saveBoolean(Constants.loginStatus,true);
                        myPreferences.saveBoolean(Constants.otpVerification,false);
                        myPreferences.saveString(Constants.mobileNumber,loginResponse.data.mobileNumber);
                        myPreferences.saveString(Constants.mobileNumber,loginResponse.data.emailAddress);
                        myPreferences.saveString(Constants.agentCode,loginResponse.data.agentCode);
                        myPreferences.saveString(Constants.userId,String.valueOf(loginResponse.data.id));
                        Intent intent = new Intent(Login.this, login_otp.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

   // https://newbedev.com/preemptive-basic-auth-with-httpurlconnection
     class HTTPReqTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            String line = null;
            try {
                JSONObject object = new JSONObject();
                try {
                    object.put("mobile","9876543210");
                    object.put("password","pradeep");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                URL url = new URL("https://api.payertrust.in/public/api/v1/login");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Authorization", "Basic cnpwX2xpdmVfbGhEamtOdzBNNHE1MHU6bXk4Z0kxS3RiNjltV2RaMU1JZkZuMUFK");
                urlConnection.setRequestProperty("Accept", "application/json");

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                //urlConnection.setDoInput(true);
                urlConnection.setChunkedStreamingMode(0);

                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        out, "UTF-8"));
                writer.write(object.toString());
                writer.close();
                out.close();

                int code = urlConnection.getResponseCode();
                if (code !=  201) {
                    throw new IOException("Invalid response from server: " + code);
                }

                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));

                while ((line = rd.readLine()) != null) {
                    Log.i("data", line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return line;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}