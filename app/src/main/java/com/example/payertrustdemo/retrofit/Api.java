package com.example.payertrustdemo.retrofit;

import com.example.payertrustdemo.model.LoginRequest;
import com.example.payertrustdemo.model.LoginResponse;
import com.example.payertrustdemo.util.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://api.payertrust.in/public/index.php/";

    @Headers({"Content-Type: application/json",
            "Authorization: Basic cnpwX2xpdmVfbGhEamtOdzBNNHE1MHU6bXk4Z0kxS3RiNjltV2RaMU1JZkZuMUFK"})
    @POST("api/v1/login")
    Call<LoginResponse> login(@Body String loginRequest);
//
//    @POST("/api/users")
//    Call<User> createUser(@Body User user);
//
//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
