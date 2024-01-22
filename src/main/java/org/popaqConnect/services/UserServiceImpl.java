package org.popaqConnect.services;

import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.repositories.UserRepository;
import org.popaqConnect.services.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void save(Client newClient) {
         userRepository.save(newClient);
    }
}
