package com.example.rnpt.connection.authorization;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CenterConnectionAutorization {


    public void postmessage(){
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
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
