package org.popaqConnect.utils;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.TrainingRequest;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;

public class  Mapper {

    public static Client mapClient(RegisterRequest registerRequest){
        Client client = new Client();
        client.setFirstName(registerRequest.getFirstName());
        client.setLastName(registerRequest.getLastName());
        client.setEmail(registerRequest.getEmail());
        client.setPassword(registerRequest.getPassword());
        client.setAddress(registerRequest.getAddress());
        client.setPhoneNumber(registerRequest.getPhoneNumber());
        client.setAge(registerRequest.getAge());
        client.setPassword(registerRequest.getPassword());
        return client;
    }
    public static Trainee mapTrainee(RegisterRequest registerRequest) {
        Trainee trainee = new Trainee();
        trainee.setFirstName(registerRequest.getFirstName());
        trainee.setLastName(registerRequest.getLastName());
        trainee.setAge(registerRequest.getAge());
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

    public static String bookServiceHeadingToServiceProvider(String firstName,String description,String time,String bookingId){
        String emailToServiceProvider =String.format( """
                Hello: %s 
                Your Service Has been Booked. 
                
                Kindly Look below For the description of the Client requirement.
                 
                 Job Requirement:
                 
                 DESCRIPTION: %s
                 
                 Duration: %s
                 
                 BookingId: %s
                Kindly login to accept the request""",firstName,description,time,bookingId);
        return emailToServiceProvider;

    }

    public static String trainingApplicationEmail(String firstName, String courseCode, TrainingRequest request) {
            String emailToServiceProvider =String.format( """
                    Dear %s 
                        I hope this email finds you well. 
                        We are pleased to inform you that a new trainee has applied for training. 
                        
                        Here are the details:
                                        
                        Trainee Information:
                        - Email:  %s
                        
                        - About Trainee: %s
                        
                        - Preferred Start Date: %s
                        
                        - Preferred End Date:  %s
                        
                        - courseCode:  %s
                    
                    Kindly login to accept the request""",firstName,request.getTraineeEmail(),request.getAboutYou(),request.getStartDate(),request.getEndDate(),courseCode);
            return emailToServiceProvider;

    }
}
