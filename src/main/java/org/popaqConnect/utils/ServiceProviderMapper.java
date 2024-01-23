package org.popaqConnect.utils;

import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.ServiceProviderRegisterRequest;

public class ServiceProviderMapper {
    public static ServiceProvider serviceProviderMap(ServiceProviderRegisterRequest registerRequest){
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setFirstName(registerRequest.getFirstName());
        serviceProvider.setLastName(registerRequest.getLastName());
        serviceProvider.setEmail(registerRequest.getEmail());
        serviceProvider.setAddress(registerRequest.getAddress());
        serviceProvider.setBioData(registerRequest.getBioData());
        serviceProvider.setPassword(registerRequest.getPassword());
        serviceProvider.setChargePerHour(registerRequest.getChargePerHour());
        Job job = new Job();
        serviceProvider.setJob(job);
        return serviceProvider;
    }

}
