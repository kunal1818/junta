package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.model.SignUpResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.customview.MaterialEditText;

import java.util.HashMap;

import io.paperdb.Paper;
import okhttp3.RequestBody;

/**
 * edit number activity
 */
public class EditNumberActivity extends BaseActivity {
    private TextView tvHeader;
    private MaterialEditText etnumber;
    private Button btnSubmit;
    private SignUpResponse signUpResponse;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);
        Paper.init(this);
        init();
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                HashMap<String, RequestBody> params = new MultipartParams.Builder().add("newNumber", etnumber.getText()).build().getMap();
                RestClient.getApiInterface().editNumber("bearer " + signUpResponse.getData().getAccessToken(), params)
                        .enqueue(new ResponseResolver<SignUpResponse>(EditNumberActivity.this, true, true) {
                            @Override
                            public void success(final SignUpResponse signUpResponse1) {
                                Toast.makeText(EditNumberActivity.this, "Number Edited", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void failure(final APIError error) {

                            }
                        });
            }
        });

    }

    /**
     * initialization
     */
    private void init() {
        signUpResponse = Paper.book().read("Userdata");
        tvHeader = (TextView) findViewById(R.id.custom_header);
        tvHeader.setText("Change Mobile Number");
        etnumber = (MaterialEditText) findViewById(R.id.et_number);
        btnSubmit = (Button) findViewById(R.id.btn_submit);


    }


}
