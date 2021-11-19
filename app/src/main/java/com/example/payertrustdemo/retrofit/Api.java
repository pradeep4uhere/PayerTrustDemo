package com.example.payertrustdemo.retrofit;

import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.AddAccountResponse;
import com.example.payertrustdemo.model.AddFundAccountResponse;
import com.example.payertrustdemo.model.AddFundContactResponse;
import com.example.payertrustdemo.model.BankListResponse;
import com.example.payertrustdemo.model.ContactResponse;
import com.example.payertrustdemo.model.CreateContactResponse;
import com.example.payertrustdemo.model.LoginResponse;
import com.example.payertrustdemo.model.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://api.payertrust.in/public/index.php/";

//    @Headers({"Content-Type: application/json",
//            "Authorization: Basic cnpwX2xpdmVfbGhEamtOdzBNNHE1MHU6bXk4Z0kxS3RiNjltV2RaMU1JZkZuMUFK"})
    @POST("api/v1/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("mobile") String mobile, @Field("password") String password);

    @POST("api/v1/signup")
    @FormUrlEncoded
    Call<RegistrationResponse> signup(@Field("mobile") String mobile, @Field("first_name") String firstName,
                                      @Field("last_name") String lastName, @Field("email") String email,
                                      @Field("password") String password, @Field("confirm_password") String confirmPassword);

    @POST("api/v1/contactlist")
    @FormUrlEncoded
    Call<ContactResponse> getAllContact(@Field("user_id") String userId);

    @POST("api/v1/createcontact")
    @FormUrlEncoded
    Call<CreateContactResponse> createContact(@Field("user_id") String userId,
                                              @Field("name") String name,
                                              @Field("mobile_number") String mobile, @Field("email_address") String email);

    @POST("api/v1/allbanklist")
    Call<BankListResponse> getAllBank();

    @POST("api/v1/addaccount")
    @FormUrlEncoded
    Call<AddAccountResponse> addBankAccount(@Field("user_id") String userId, @Field("contact_id") String contactId,
                                            @Field("beneficiary_name") String beneficiaryName, @Field("account_type") String accountType,
                                            @Field("account_number") String accountNumber, @Field("ifsc_code") String ifscCode,
                                            @Field("master_bank_id") String masterBankId);

    @POST("api/v1/accountlist")
    @FormUrlEncoded
    Call<AccountListresponse> getAccountList(@Field("contact_id") String contactId, @Field("user_id") String userId);

    @POST("api/v1/addfundcontact")
    @FormUrlEncoded
    Call<AddFundContactResponse> addFundContact(@Field("user_id") String userId, @Field("payout_contact_id") String contactId,
                                                @Field("payout_bank_id") String bankid);

    @POST("api/v1/addfundaccount")
    @FormUrlEncoded
    Call<AddFundAccountResponse> addFundAccount(@Field("user_id") String userId, @Field("payout_bank_contact_id") String bankContactId,
                                                @Field("payout_account_id") String accountId, @Field("payout_user_contact_id") String userContactId);

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
