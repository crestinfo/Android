package com.example.server.googlemapdemo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by server on 5/4/17.
 */

public interface ApiCallInterface {


    @POST("your_api_child_url")
    Call<AmountData> getTypeData(@Body AmountData user);

    @FormUrlEncoded
    @POST("your_api_child_url")
    Call<ResponseBody> getType(@QueryMap Map<String, String> param);

}
