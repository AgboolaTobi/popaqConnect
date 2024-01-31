package org.popaqConnect.utils;
import org.popaqConnect.data.CourseStatus;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.TrainingRequest;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;

public class  Mapper {

    public static Client mapClient(RegisterRequest registerRequest){
        Client client = new Client();
        client.setUserName(registerRequest.getUserName());
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
        String emailToServiceProvider = String.format("""
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
                                            
                        Kindly login to accept the request""", firstName, request.getTraineeEmail(),
                request.getAboutYou(), request.getStartDate(), request.getEndDate(), courseCode);
        return emailToServiceProvider;
    }

    public static String mapTraineeApplication(String courseCode, CourseApplication course) {
        String description;
        if (course.getCourseStatus() == CourseStatus.LEARNING) {
            description = String.format("""
                    Dear %s
                    I trust this message finds you well.
                    I would like to inform you about the status of your training application.
                    We are delighted to inform you that your application has been accepted. 
                    Congratulations!You can contact your tranier through his email %s
                    Best Regards.""", course.getTraineeEmail(), course.getTraineeEmail());
        } else {
            description = String.format("""
                    Dear %s
                    I trust this message finds you well.
                    I would like to inform you about the status of your training application.
                    Unfortunately your application was rejected,We appreciate your interest and encourage you to consider applying to other trainers.
                    Best regards.""", course.getTraineeEmail());
        }
        return description;

    }
}
