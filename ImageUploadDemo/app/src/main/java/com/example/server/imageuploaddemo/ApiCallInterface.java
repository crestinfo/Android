package com.example.server.imageuploaddemo;

import com.example.server.imageuploaddemo.POJO.UpdateProfile.UpdateProfile;
import com.example.server.imageuploaddemo.POJO.UserData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by server on 5/17/17.
 */

public interface ApiCallInterface {

    @FormUrlEncoded
    @POST("api/?action=user_profile")  //Sub Url Of Your Serer
    Call<UserData> getUser(@Field("id") String uid);//Request params

    @Multipart
    @POST("api/?action=upload_images") //Sub Url Of Your Serer
    Call<UpdateProfile> uploadProfile(
            @Part("id") RequestBody uid,    //Request params
            @Part MultipartBody.Part file);  //Multipart Param for uploading Image file
}
