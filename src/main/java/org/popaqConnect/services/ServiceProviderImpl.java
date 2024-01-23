package org.popaqConnect.services;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.BookType;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.AcceptBookingRequest;
import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceProviderImpl implements ServiceProviderServices {
    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Autowired
    BookServices bookServices;
    @Autowired
    AdminService adminService;
    @Override
    public Optional<ServiceProvider> findUser(String serviceProviderEmail) {
        return serviceProviderRepository.findByEmail(serviceProviderEmail);
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        ServiceProvider serviceProvider = Mapper.mapServiceProvider(registerRequest);
        serviceProviderRepository.save(serviceProvider);
    }

    @Override
    public void acceptClientBookRequest(AcceptBookingRequest bookingRequest) {
        Optional <ServiceProvider> serviceProvider = serviceProviderRepository.findByEmail(bookingRequest.getEmail());
        userExist(bookingRequest.getEmail());
        bookServices.setBookType(serviceProvider.get().getEmail(),bookingRequest);
        Book book = bookServices.findABookRequest(bookingRequest.getId() ,serviceProvider.get().getEmail());
        adminService.sendAcceptRequest(bookingRequest.getId(),book.getClientEmail());



    }
    private void userExist(String email){
        Optional <ServiceProvider> serviceProvider = serviceProviderRepository.findByEmail(email);
       if(serviceProvider.isEmpty())throw new  UserExistException("User doesn't exist");
    }
}
