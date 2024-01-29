package org.popaqConnect.utils;

import org.popaqConnect.data.JobCategory;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.ServiceProviderRegisterRequest;

public class ServiceProviderMapper {
    public static ServiceProvider serviceProviderMap(ServiceProviderRegisterRequest registerRequest){
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setEmail(registerRequest.getEmail());
        serviceProvider.setAddress(registerRequest.getAddress());
        serviceProvider.setBioData(registerRequest.getBioData());
        serviceProvider.setPassword(registerRequest.getPassword());
        serviceProvider.setChargePerHour(registerRequest.getChargePerHour());
        return serviceProvider;
    }

}
