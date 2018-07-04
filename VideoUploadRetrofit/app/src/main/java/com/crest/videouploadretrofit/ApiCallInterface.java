package com.crest.videouploadretrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by brittany on 6/29/17.
 */

public interface ApiCallInterface {
    @Multipart
    @POST("api/Upload_video")
    Call<UploadVideoPojo> uploadData(
            @Part("id") RequestBody i_user_id,
            @Part("title") RequestBody v_title,
            @Part("duration") RequestBody v_duration,
            @Part MultipartBody.Part file);
}
