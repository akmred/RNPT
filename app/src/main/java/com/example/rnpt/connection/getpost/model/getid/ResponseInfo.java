package com.example.rnpt.connection.getpost.model.getid;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "SendMessageResponse", strict = false)
@Namespace(prefix = "xmlns", reference = "urn://x-artefacts-gnivc-ru/inplat/servin/OpenApiAsyncMessageConsumerService/types/1.0")
public final class ResponseInfo {

    @Element(name = "MessageId", required = false)
//    @Namespace(prefix = "tns", reference = "http://saidas.curitiba.org/xsd")
   public final String messageId;

//    @Element(name = "ExpireTime", required = false)
//    @Namespace(prefix = "tns", reference = "http://saidas.curitiba.org/xsd")
//    final String expireTime;

    private ResponseInfo(
            @Element(name = "messageId") final String MessageId
        //    @Element(name = "ExpireTime") final String expireTime
    ) {
        this.messageId = MessageId;
      //  this.expireTime = expireTime;
    }

}