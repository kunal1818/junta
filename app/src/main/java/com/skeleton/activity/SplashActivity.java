package com.skeleton.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skeleton.R;
import com.skeleton.fcm.FCMTokenInterface;
import com.skeleton.fcm.MyFirebaseInstanceIdService;
import com.skeleton.model.SignUpResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.Util;
import com.skeleton.util.dialog.CustomAlertDialog;

import io.paperdb.Paper;

/**
 * Landing Page of the App
 */
public class SplashActivity extends BaseActivity implements FCMTokenInterface {
    private static final String TAG = SplashActivity.class.getName();
    private Dialog mDialog;
    private String accessToken;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();

    }

    /**
     *
     */
    private synchronized void init() {
        if (!Util.isNetworkAvailable(SplashActivity.this)) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                    .setMessage(R.string.error_internet_not_connected)
                    .setPositiveButton(R.string.text_retry, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            init();
                        }
                    })
                    .setNegativeButton(getString(R.string.text_exit), new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    })
                    .show();
            return;
        }
        if (!checkPlayServices()) {
            return;
        }
        MyFirebaseInstanceIdService.setCallback(this);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SCREEN_OVERLAY) {
            if (Settings.canDrawOverlays(this)) {
                init();
            }
        } else if (requestCode == REQ_CODE_PLAY_SERVICES_RESOLUTION
                && resultCode == Activity.RESULT_OK) {
            init();
        }
    }

    /**
     * @return true if google play service present & updated
     * false if not presented or not updated
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, REQ_CODE_PLAY_SERVICES_RESOLUTION)
                        .show();
            } else {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                        .setMessage(R.string.error_device_not_supported)
                        .setPositiveButton(R.string.text_ok, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                            @Override
                            public void onClick() {
                                finish();
                            }
                        })
                        .show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onTokenReceived(final String token) {
        //   Log.e(TAG, token);
        // startActivity(new Intent(this, TestClassLocation.class));
        accessToken = Paper.book().read("key");
        //Toast.makeText(getApplicationContext(), accessToken, Toast.LENGTH_SHORT).show();
        if (accessToken != null) {
            android.util.Log.d("debug", accessToken);
            RestClient.getApiInterface().profile(accessToken).enqueue(new ResponseResolver<SignUpResponse>(this, true, true) {
                @Override
                public void success(final SignUpResponse signUpResponse) {
                    startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
//                    if (!signUpResponse.getData().getUserDetails().getPhoneVerified()) {
//                        startActivity(new Intent(SplashActivity.this, OtpActivity.class));
//                    } else {
//                        if (signUpResponse.getData().getUserDetails().getStep1CompleteOrSkip()
//                                && signUpResponse.getData().getUserDetails().getStep2CompleteOrSkip()) {
//                            startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
//                        } else {
//                            Log.e("error", "error");
//                        }
//                    }
                }

                @Override
                public void failure(final APIError error) {
                    Log.d("debug", error.getMessage());
                }
            });
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));

        }
    }

    @Override
    public void onFailure() {
        if (isFinishing()) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /**
                 * in failure make another attempt to get device token
                 * or finish activity which in turn finish application.
                 */
                MyFirebaseInstanceIdService.retry(SplashActivity.this);
            }
        });
    }

    /**
     * @return boolean value of permission overlay
     */
    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQ_CODE_SCREEN_OVERLAY);
            return false;
        } else {
            return true;
        }
    }

}
