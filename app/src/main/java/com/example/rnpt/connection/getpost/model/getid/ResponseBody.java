package com.example.rnpt.connection.getpost.model.getid;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
@Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/")
public class ResponseBody {

    @Element(name = "MasterToken", required = false)
    @Namespace(prefix = "ns1", reference = "urn://x-artefacts-gnivc-ru/ais3/kkt/AuthService/types/1.0")
    public ResponseData requestData;

    private ResponseBody(
            @Element(name = "MasterToken") final ResponseData requestData
    ) {
        this.requestData = requestData;
    }

}