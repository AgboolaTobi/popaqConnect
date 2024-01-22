package org.popaqConnect.services.ServiceInterfaces;

import org.popaqConnect.dtos.requests.RegisterRequest;

public interface ClientService {

    void register(RegisterRequest registerRequest);
}
