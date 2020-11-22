package com.example.rnpt.connection.getpost;

import androidx.annotation.NonNull;

import com.example.rnpt.connection.getpost.model.getid.ResponseEnvelope;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenRNPT {

    @POST("/mock/consumer/AsyncService")
    Call<ResponseEnvelope> postQueryGetID(@NonNull @Body RequestBody content);

    @POST("/mock/consumer/AsyncService")
    Call<ResponseEnvelope> postQueryID(@NonNull @Body RequestBody content);

}
