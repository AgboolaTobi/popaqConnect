package org.popaqConnect.services;

import lombok.Data;
import org.popaqConnect.data.models.Client;
import org.springframework.stereotype.Service;


public  interface UserService {
    void save(Client newClient);
}
