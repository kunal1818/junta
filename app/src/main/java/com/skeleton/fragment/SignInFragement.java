package com.skeleton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.model.SignUpResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;

/**
 * Created by mark63 on 11/5/17.
 */

public class SignInFragement extends Fragment implements View.OnClickListener {

    private static final int APP_VERSION = 101;
    private MaterialEditText etEmail, etPassword;
    private Button btnSignIn;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_sign_in, container, false);
        init(view);
        return view;
    }

    /**
     * @param view inflated view
     */
    private void init(final View view) {
        etEmail = (MaterialEditText) view.findViewById(R.id.etEmailPhoneNo);
        etPassword = (MaterialEditText) view.findViewById(R.id.etPassword);
        btnSignIn = (Button) view.findViewById(R.id.btnSignInPage);
        btnSignIn.setOnClickListener(this);


    }

    /**
     * @return view true if all valid else false
     */
    private boolean validateForm() {
        if (!(ValidateEditText.checkEmail(etEmail))) {
            return false;
        } else if (!(ValidateEditText.checkPassword(etPassword, false))) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View v) {
        if (validateForm()) {
            CommonParams params = new CommonParams.Builder()
                    .add("email", etEmail.getText().toString())
                    .add("password", etPassword.getText().toString())
                    .add("deviceType", "ANDROID")
                    .add("language", "EN")
                    .add("deviceToken", "XYZ")
                    .add("flushPreviousSessions", true)
                    .add("appVersion", APP_VERSION)
                    .build();

            RestClient.getApiInterface().login(null, params.getMap()).enqueue(new ResponseResolver<SignUpResponse>(getContext(), true, true) {
                @Override
                public void success(final SignUpResponse signUpResponse) {
                    Toast.makeText(getContext(), signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(final APIError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}



