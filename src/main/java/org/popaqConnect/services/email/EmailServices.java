package org.popaqConnect.services.email;

import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.response.EmailResponse;

public interface EmailServices {
    EmailResponse send(EmailRequest emailRequest);

}
