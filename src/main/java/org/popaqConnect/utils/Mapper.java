package org.popaqConnect.utils;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
public class  Mapper {

    public static Client mapClient(RegisterRequest registerRequest){
        Client client = new Client();
        client.setFirstName(registerRequest.getUserName());
        client.setEmail(registerRequest.getEmail());
        client.setPassword(registerRequest.getPassword());
        client.setAddress(registerRequest.getAddress());
        client.setPhoneNumber(registerRequest.getPhoneNumber());
        client.setPassword(registerRequest.getPassword());
        return client;
    }
    public static Trainee mapTrainee(RegisterRequest registerRequest) {
        Trainee trainee = new Trainee();
        trainee.setFirstName(registerRequest.getUserName());
        trainee.setPhoneNumber(registerRequest.getPhoneNumber());
        trainee.setAddress(registerRequest.getAddress());
        trainee.setEmail(registerRequest.getEmail());
        trainee.setPassword(registerRequest.getPassword());
        return trainee;
    }


    public static EmailRequest mapEmailRequest(String email,String description,String subject) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setReciepent(email);
        emailRequest.setMessage(description);
        emailRequest.setSubject(subject);
        return emailRequest;
    }

    public static String bookServiceHeadingToServiceProvider(String serviceProviderEmail,String description,String time){
        String emailToServiceProvider =String.format( """
                Hello: %s 
                Your Service Has been Booked Kindly Look below For the description of the Client
                 requirement
                 DESCRIPTION: %s
                 Time: %s
                Kindly login to accept the request""",serviceProviderEmail,description,time);
        return emailToServiceProvider;

    }

    public static ServiceProvider mapServiceProvider(RegisterRequest registerRequest) {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setUserName(registerRequest.getUserName());
        serviceProvider.setEmail(registerRequest.getEmail());
        serviceProvider.setAddress(registerRequest.getAddress());
        serviceProvider.setPhoneNumber(registerRequest.getPhoneNumber());
        serviceProvider.setPassword(registerRequest.getPassword());
        return serviceProvider;
    }
}
