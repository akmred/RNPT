package com.example.rnpt.connection.getpost.model.getid;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;


@Root(name = "Envelope")
@Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/")
public final class ResponseEnvelope {

    @Element(name = "Body", required = false)
    @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/")
    public ResponseBody body;

    private ResponseEnvelope(
            @Element(name = "Body") final ResponseBody body
    ) {
        this.body = body;
    }



}