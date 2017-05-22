package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.model.SignUpResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.customview.MaterialEditText;

import java.util.HashMap;

import io.paperdb.Paper;


/**
 * otp activity
 */
public class OtpActivity extends BaseActivity {
    private static final int RESULT_OK = 1;
    private String mPhoneno, mCountryCode, mOtpCode;
    private Button btnresend, btnEditNumber, btnVerify;
    private MaterialEditText etOtp1, etOtp2, etOtp3, etOtp4;
    private String accessToken;
    private SignUpResponse signUpResponse;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Paper.init(this);
        init();
        btnresend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                resendOtpToUser();


            }
        });
        btnEditNumber.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                resetNumber();
            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                mOtpCode = etOtp1.getText().toString() + etOtp2.getText().toString() + etOtp3.getText().toString() + etOtp4.getText().toString();
                verify();

            }
        });

    }

    /**
     * initialization
     */
    private void init() {
        signUpResponse = Paper.book().read("Userdata");
        mPhoneno = signUpResponse.getData().getUserDetails().getPhoneNo();
        mCountryCode = signUpResponse.getData().getUserDetails().getCountryCode();
        btnresend = (Button) findViewById(R.id.btn_resetOtp);
        btnEditNumber = (Button) findViewById(R.id.btn_editNoOtp);
        btnVerify = (Button) findViewById(R.id.btn_verify);
        etOtp1 = (MaterialEditText) findViewById(R.id.et_otp1);
        etOtp2 = (MaterialEditText) findViewById(R.id.et_otp2);
        etOtp3 = (MaterialEditText) findViewById(R.id.et_otp3);
        etOtp4 = (MaterialEditText) findViewById(R.id.et_otp4);
    }

    /**
     * resend ipty
     */
    private void resendOtpToUser() {
        accessToken = Paper.book().read("key");
        RestClient.getApiInterface().resendOtp(accessToken).enqueue(new ResponseResolver<SignUpResponse>(getApplication()) {
            @Override
            public void success(final SignUpResponse signUpResponse2) {
                Toast.makeText(OtpActivity.this, "OTP Resend SuccessFull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(final APIError error) {

            }
        });
    }

    /**
     * reset number
     */
    private void resetNumber() {
        Intent intent = new Intent(OtpActivity.this, EditNumberActivity.class);
        startActivityForResult(intent, RESULT_OK);
    }

    /**
     * tto verify otp
     */
    private void verify() {
        HashMap<String, String> commomParams = new CommonParams.Builder()
                .add("countryCode", mCountryCode)
                .add("phoneNo", mPhoneno)
                .add("OTPCode", mOtpCode).build().getMap();
        RestClient.getApiInterface().verify("bearer " + signUpResponse.getData().getAccessToken(), commomParams)
                .enqueue(new ResponseResolver<SignUpResponse>(OtpActivity.this, true, true) {
                    @Override
                    public void success(final SignUpResponse signUpResponse1) {
                        Toast.makeText(OtpActivity.this, "VERIFIED", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void failure(final APIError error) {

                    }
                });


    }
}
