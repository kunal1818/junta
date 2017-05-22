package com.skeleton.retrofit;


import com.skeleton.model.SignUpResponse;
import com.skeleton.model.profile.ConstantResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;

import static com.skeleton.constant.ApiKeyConstant.AUTHORIZATION;

/**
 * Developer: Saurabh Verma
 * Dated: 27-09-2016.
 */
public interface ApiInterface {
    String UPDATE_LOCATION = "api/v1/user/updateLocation";
    String REGISTER = "api/user/register";
    String LOGIN = "api/user/login";
    String PROFILE = "api/user/getProfile";
    String RESEND = "api/user/resendOTP";
    String EDIT_NUMBER = "api/user/updateProfile";
    String VERIFY = "api/user/verifyOTP";
    String PROFILE_CONSTANTS = "api/profile/constants";


//    /**
//     * @param map
//     * @return
//     */
//    @Multipart
//    @POST("api/v1/user/createUser")
//    Call<LoginResponse> register(@PartMap HashMap<String, RequestBody> map);
//
//
//    /**
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/socialLogin")
//    Call<LoginResponse> socialLogin(@FieldMap HashMap<String, String> map);
//
//    /**
//     * @param authorization
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/loginToken")
//    Call<LoginResponse> accessTokenLogin(@Header(AUTHORIZATION) String authorization,
//                                         @FieldMap HashMap<String, String> map);
//
//
//    /**
//     * @param email
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/v1/user/forgotpassword")
//    Call<CommonResponse> forgotPassword(@Field("email") String email);
//
//    /**
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/loginCredential")
//    Call<LoginResponse> login(@FieldMap HashMap<String, String> map);

    /**
     * Update location call.
     *
     * @param authorization the authorization
     * @param map           the map
     * @return the call
     */
    @FormUrlEncoded
    @POST(UPDATE_LOCATION)
    Call<CommonParams> updateLocation(@Header(AUTHORIZATION) String authorization,
                                      @FieldMap HashMap<String, String> map);

    /**
     * @param map key value pair of parameters sending to respone
     * @return SignUpResponse
     */
    @Multipart
    @POST(REGISTER)
    Call<SignUpResponse> register(@PartMap HashMap<String, RequestBody> map);

    /**
     * @param authorization auth
     * @param map           email and password of the user
     * @return login details
     */
    @FormUrlEncoded
    @POST(LOGIN)
    Call<SignUpResponse> login(@Header(AUTHORIZATION) String authorization,
                               @FieldMap HashMap<String, String> map);

    /**
     * @return profile constants
     */
    @GET(PROFILE_CONSTANTS)
    Call<ConstantResponse> profile_constants();

    /**
     * @param authorization auth
     * @return profile
     */
    @GET(PROFILE)
    Call<SignUpResponse> profile(@Header(AUTHORIZATION) String authorization);

    /**
     * @param authorization auth
     * @param map           the map
     * @return number after editting
     */
    @Multipart
    @PUT(EDIT_NUMBER)
    Call<SignUpResponse> editNumber(@Header(AUTHORIZATION) String authorization,
                                    @PartMap Map<String, RequestBody> map);

    /**
     * @param authorization auth
     * @param map           hash map
     * @return verification
     */
    @PUT(VERIFY)
    Call<SignUpResponse> verify(@Header(AUTHORIZATION) String authorization,
                                @Body HashMap<String, String> map);

    /**
     * @param authorization auth
     * @return otp
     */
    @PUT(RESEND)
    Call<SignUpResponse> resendOtp(@Header(AUTHORIZATION) String authorization);


}

