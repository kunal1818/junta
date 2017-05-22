package com.skeleton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.skeleton.R;
import com.skeleton.activity.OtpActivity;
import com.skeleton.model.SignUpResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;
import com.skeleton.util.imagepicker.ImageChooser;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.paperdb.Paper;

/**
 * Created by mark63 on 11/5/17.
 */

public class SignUpFragment extends BaseFragment {
    private MaterialEditText etName, etPhoneNum, etEmail, etDOB, etPassword, etConfirmPassword;
    private Button btnSignUp;
    private ImageView ivProfilePic;
    private CheckBox cbTOS;
    private RadioGroup rgGender;
    private int mSelectedGender, mGender;
    private String mName, mPhoneNum, mEmail, mPassword, mDOB;
    private File mProfilePic;
    private ImageChooser chooser;
    private RadioButton rbMale, rbFemale;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_sign_up, container, false);
        init(view);
        return view;
    }

    /**
     * @param view inflated view
     */
    private void init(final View view) {
        etName = (MaterialEditText) view.findViewById(R.id.etName);
        etPhoneNum = (MaterialEditText) view.findViewById(R.id.etPhoneNo);
        etEmail = (MaterialEditText) view.findViewById(R.id.etEmail);
        etPassword = (MaterialEditText) view.findViewById(R.id.etPasswordSignUp);
        etDOB = (MaterialEditText) view.findViewById(R.id.etDOB);
        etConfirmPassword = (MaterialEditText) view.findViewById(R.id.etConfirmPasswordSignUp);
        rgGender = (RadioGroup) view.findViewById(R.id.rgGender);
        ivProfilePic = (ImageView) view.findViewById(R.id.ivCircular);
        rbMale = (RadioButton) view.findViewById(R.id.radiobtnMale);
        rbFemale = (RadioButton) view.findViewById(R.id.radiobtnFemale);
        cbTOS = (CheckBox) view.findViewById(R.id.cbAccept);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUpPage);
        btnSignUp.setOnClickListener(this);
        ivProfilePic.setOnClickListener(this);

    }


    @Override
    public void onClick(final View v) {
        super.onClick(v);
        int id = v.getId();
        switch (id) {
            case R.id.btnSignUpPage:
                postData();
                break;
            case R.id.ivCircular:
                chooser = new ImageChooser.Builder(SignUpFragment.this).build();
                chooser.selectImage(new ImageChooser.OnImageSelectListener() {
                    @Override
                    public void loadImage(final List<ChosenImage> list) {
                        mProfilePic = new File(list.get(0).getOriginalPath());
                        Glide.with(getContext()).load(mProfilePic).into(ivProfilePic);
                    }

                    @Override
                    public void croppedImage(final File mCroppedImage) {

                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * Extract data from edit text
     */
    private void extractData() {
        mName = etName.getText().toString().trim();
        mPhoneNum = etPhoneNum.getText().toString().trim();
        mEmail = etEmail.getText().toString().trim();
        mDOB = etDOB.getText().toString().trim();
        mPassword = etPassword.getText().toString().trim();
        if (rbMale.isChecked()) {
            mGender = 0;
        } else {
            mGender = 1;
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        chooser.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        chooser.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * @return true if all is valid else false
     */
    private boolean validateForm() {
        mSelectedGender = rgGender.getCheckedRadioButtonId();
        if (!(ValidateEditText.checkName(etName, true))) {
            return false;
        } else if (!ValidateEditText.checkPhoneNumber(etPhoneNum)) {
            return false;
        } else if (!(ValidateEditText.checkEmail(etEmail))) {
            return false;
        } else if (!ValidateEditText.checkPassword(etPassword, false)) {
            return false;
        } else if (!ValidateEditText.checkPassword(etConfirmPassword, true)) {
            return false;
        } else if (!ValidateEditText.comparePassword(etPassword, etConfirmPassword)) {
            return false;
        } else if (!cbTOS.isChecked()) {
            Toast.makeText(getContext(), R.string.error_tos, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mSelectedGender == -1) {
            Toast.makeText(getContext(), R.string.error_gender, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!checkDOB(etDOB)) {
            return false;
        } else if (mProfilePic == null) {
            Toast.makeText(getContext(), R.string.error_profile_pic, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /**
     * Checks if date is in valid format
     *
     * @param editText : editTextDOB containing date
     * @return : true if valid, else returns false
     */
    private boolean checkDOB(final MaterialEditText editText) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = editText.getText().toString();
        try {
            mDOB = df.parse(s).toString();
            return true;

        } catch (ParseException e) {
            editText.setError(getString(R.string.error_invalid_data));
            return false;
        }
    }

    /**
     * Called when sign button is clicked and post the data to server
     */
    private void postData() {
        if (validateForm()) {
            extractData();
            MultipartParams params = new MultipartParams.Builder()
                    .add("firstName", mName)
                    .add("dob", mDOB)
                    .add("countryCode", "+91")
                    .add("phoneNo", mPhoneNum)
                    .add("email", mEmail)
                    .add("password", mPassword)
                    .add("gender", mGender)
                    .add("deviceToken", "XYZ")
                    .add("appVersion", "101")
                    .add("language", "EN")
                    .add("deviceType", "ANDROID")
                    .addFile("profilePic", mProfilePic).build();
            RestClient.getApiInterface().register(params.getMap()).enqueue(new ResponseResolver<SignUpResponse>(getContext()
                    , true) {
                @Override
                public void success(final SignUpResponse signUpResponse) {
                    Log.d("debug", String.valueOf(signUpResponse.getStatusCode()));
                    if ("200".equals(String.valueOf(signUpResponse.getStatusCode()))) {
                        Log.d("debug", "success");
                        Toast.makeText(getContext(), signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Paper.book().write("key", "bearer " + signUpResponse.getData().getAccessToken());
                        Paper.book().write("Userdata", signUpResponse);
                        Log.d("debug", "added everything ");
                        startActivity(new Intent(getContext(), OtpActivity.class));

                    }
                }

                @Override
                public void failure(final APIError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
