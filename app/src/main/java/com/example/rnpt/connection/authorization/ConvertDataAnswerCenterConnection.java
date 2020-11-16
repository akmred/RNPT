package com.example.rnpt.connection.authorization;

import android.os.Handler;
import android.os.HandlerThread;

import okhttp3.Response;

public class ConvertDataAnswerCenterConnection {
    String content;
    String token = "test76787789789kjhkj";
    HandlerThread handlerThread;
    Handler handler;
    private OnRequerConvertAnswer listener;

    public ConvertDataAnswerCenterConnection(String content, OnRequerConvertAnswer listener) {

        this.content = content;
        this.listener = listener;

        handlerThread = new HandlerThread("HandleThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

       handler.post(new Runnable() {
           @Override
           public void run() {
               listener.onComplete(new DataAuthorization(token));
           }
       });


    }

    public DataAuthorization GetToken(){

     return new DataAuthorization(token);
    }

    interface OnRequerConvertAnswer{
        void onComplete(DataAuthorization dataAuthorization);
        void onError(String textError);

    }
}
