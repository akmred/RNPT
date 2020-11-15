package com.example.rnpt.connection.authorization;

import android.os.Handler;
import android.os.HandlerThread;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CenterConnectionAutorization {
    HandlerThread handlerThread;
    Handler handler;
    Response response;
    final String URL = "http://localhost:8087/mock/consumer/authService";
    final  String MASTER_TOKEN = "4WKOCoOLArPnF1ijCkQBH6CKNWeO2cfBvZlWJqNCpnuopMD0ISGSefPGILAQI2n4rntacA3X1oc1QCHuYOG0zx6M0wGE7x9saHMJdBIJnUyL4ePu7pvK4UBq0OMp00DS";
    private OnResponseCompleted listener;
    
    public CenterConnectionAutorization() {
        handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());


    }

    public Response postmessage(){

        try {
                 sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return response;
    }

    private void sendMessage() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ns0:GetMessageRequest xmlns:ns0=\"urn://x-artefacts-gnivc-ru/inplat/servin/OpenApiMessageConsumerService/types/1.0\">\r\n\t\t\t<ns0:Message>\r\n\t\t\t\t<ns1:AuthRequest xmlns:ns1=\"urn://x-artefacts-gnivc-ru/ais3/kkt/AuthService/types/1.0\">\r\n\t\t\t\t\t<ns1:AuthAppInfo>\r\n\t\t\t\t\t\t<ns1:MasterToken>4WKOCoOLArPnF1ijCkQBH6CKNWeO2cfBvZlWJqNCpnuopMD0ISGSefPGILAQI2n4rntacA3X1oc1QCHuYOG0zx6M0wGE7x9saHMJdBIJnUyL4ePu7pvK4UBq0OMp00DS</ns1:MasterToken>\r\n\t\t\t\t\t</ns1:AuthAppInfo>\r\n\t\t\t\t</ns1:AuthRequest>\r\n\t\t\t</ns0:Message>\r\n\t\t</ns0:GetMessageRequest>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8087/mock/consumer/authService")
                .method("POST", body)
                .addHeader("SOAPAction", "urn:GetMessageRequest")
                .addHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)")
                .addHeader("Host", "localhost:8087")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Type", "text/xml;charset=UTF-8")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String answer = request.body().toString();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onCompleted(answer);
                    }
                });
            }
        });
    }

    public Response getResponse() {
        return response;
    }

    public void  exitThread(){

        handlerThread.quit();
    }
    
    
    
    public interface OnResponseCompleted{
        void onCompleted(String content);
    }
    
}
