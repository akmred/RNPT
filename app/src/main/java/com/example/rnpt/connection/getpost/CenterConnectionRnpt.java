package com.example.rnpt.connection.getpost;

import androidx.fragment.app.FragmentActivity;

import com.example.rnpt.R;
import com.example.rnpt.connection.getpost.model.getid.ResponseEnvelope;
import com.example.rnpt.connection.getpost.model.getid.ResponseInfo;
import com.example.rnpt.fragments.FragmentOpenRNPT;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CenterConnectionRnpt{
    private String stringRNPT;
    private OpenRNPT openRNPT;
    private ResponseInfo responseInfo;
    private FragmentActivity activity;

    public CenterConnectionRnpt(String stringRNPT, FragmentActivity activity) {
        this.stringRNPT = stringRNPT;
        this.activity = activity;
    }

    public void infoCheck() {

        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8086/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

         openRNPT = retrofit.create(OpenRNPT.class);

         // Записываем тело макета
         RequestBody requestBody = new RequestBody() {
             @Nullable
             @Override
             public MediaType contentType() {
                 return null;
             }

             @Override
             public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

                 bufferedSink.writeUtf8(stringRNPT);

             }
         };

        openRNPT.postQueryGetID(requestBody).enqueue(new Callback<ResponseEnvelope>() {
            @Override
            public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {

                responseInfo = response.body().body.requestData.info;

                RequestBody requestBodyGetRNTP = new RequestBody() {
                    @Nullable
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

                        bufferedSink.writeUtf8(String.valueOf(responseInfo.messageId));

                    }
                };

                openRNPT.postQueryID(requestBodyGetRNTP).enqueue(new Callback<ResponseEnvelope>() {
                    @Override
                    public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {

                        responseInfo = response.body().body.requestData.info;
                        //RNPT = responseInfo.RNPT;

                        FragmentOpenRNPT fragmentOpenRNPT = (FragmentOpenRNPT) activity.getSupportFragmentManager().findFragmentById(R.id.context_main);
                        fragmentOpenRNPT.downLoadInfoRNPT(responseInfo);

                    }

                    @Override
                    public void onFailure(Call<ResponseEnvelope> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<ResponseEnvelope> call, Throwable t) {

            }
        });



    }

}
