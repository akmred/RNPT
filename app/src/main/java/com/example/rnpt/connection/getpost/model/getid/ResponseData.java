package com.example.rnpt.connection.getpost.model.getid;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "autenticarUsuarioPorEmailResponse", strict = false)
@Namespace(prefix = "ns", reference = "http://business.curitiba.org")
public class ResponseData {

    @Element(name = "return", required = false)
    @Namespace(prefix = "ns", reference = "http://business.curitiba.org")
    public final ResponseInfo info;

    private ResponseData(
            @Element(name = "return") final ResponseInfo info
    ) {
        this.info = info;
    }

}
