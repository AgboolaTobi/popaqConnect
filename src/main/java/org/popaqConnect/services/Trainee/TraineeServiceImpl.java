package org.popaqConnect.services.Trainee;

import org.popaqConnect.data.CourseStatus;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.repositories.TraineeRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;
import org.popaqConnect.exceptions.*;
import org.popaqConnect.services.Admin.AdminService;
import org.popaqConnect.services.CourseApplication.CourseApplicationService;
import org.popaqConnect.services.serviceProvider.ServiceProviderServices;
import org.popaqConnect.utils.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.popaqConnect.utils.Mapper.mapTrainee;


@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private CourseApplicationService applicationService;
    @Autowired
    private ServiceProviderServices services;
    @Autowired
    AdminService adminService;
    @Override
    public void register(RegisterRequest registerRequest) {
        if (userExist(registerRequest.getEmail())) throw new UserExistException(registerRequest.getEmail()+
                " Already Exist");
        if(!Verification.verifyPassword(registerRequest.getPassword()))
            throw new InvalidDetailsException("Wrong password format");
        if(!Verification.verifyEmail(registerRequest.getEmail()))
            throw new InvalidDetailsException("Invalid email format");
        Trainee trainee = mapTrainee(registerRequest);
        traineeRepository.save(trainee);
    }
    @Override
    public void login(LoginRequest loginRequest) {
        Trainee foundTrainee = traineeRepository.findByEmail(loginRequest.getEmail());
        if (!userExist(loginRequest.getEmail())) throw new UserDoesNotExistException(loginRequest.getEmail()+
                " Doesn't Exist");
        if (!foundTrainee.getPassword().equals(loginRequest.getPassword()))
            throw new InvalidDetailsException("Incorrect Details");
        foundTrainee.setLoginStatus(true);
        traineeRepository.save(foundTrainee);
    }

    @Override
    public ApplyForTrainingResponse applyForTraining(TrainingRequest request) {
        Trainee foundTrainee = traineeRepository.findByEmail(request.getTraineeEmail());
        if(!userExist(request.getTraineeEmail()))throw new UserExistException("user does not exist");
        if(!foundTrainee.isLoginStatus())throw new AppLockedException("Kindly login");
        if(checkForPendingCourse(request.getTraineeEmail()))throw new TrainingException("Kindly complete your pending course");
        Optional<ServiceProvider> serviceProvider= services.findUser(request.getTrainerEmail());
        if(serviceProvider.isEmpty())throw new UserExistException("Trainer doesn't exist");
        if(!serviceProvider.get().isAvailableForTraining())throw new TrainingException("Trainer is not open for training");
        ApplyForTrainingResponse response = applicationService.setupCourseApplication(request);
        services.saveTrainees(foundTrainee,request.getTrainerEmail());
        adminService.sendTraineeApplicationRequest(serviceProvider.get().getUserName(),response.getMessage(),request);
        return response;

    }

    private boolean checkForPendingCourse(String email){
        List<CourseApplication> courseApplications = applicationService.findAllCourse(email);
        for(CourseApplication courseApplication: courseApplications){
            if(courseApplication.getCourseStatus() == CourseStatus.LEARNING)return true;
        }
        return false;
    }

    @Override
    public List<ServiceProvider> searchForTrainers(String email) {
        Trainee foundTrainee = traineeRepository.findByEmail(email);
        if(!userExist(email))throw new UserExistException(email + " User doesn't exist");
        if(!foundTrainee.isLoginStatus())throw new AppLockedException("Kindly Login");
        return services.findAllServiceProviderAvailableForTraining();
    }

    @Override
    public void cancelCourseApplication(CancelCourseRequest cancelCourse) {
        Trainee foundTrainee = traineeRepository.findByEmail(cancelCourse.getTraineeEmail());
        if(!userExist(cancelCourse.getTraineeEmail()))throw new UserExistException("User doesn't exist");
        if(!foundTrainee.isLoginStatus())throw new AppLockedException("Kindly Login");
        applicationService.cancelCourse(cancelCourse.getCourseCode(),cancelCourse.getTraineeEmail());
        services.removeUser(cancelCourse.getTraineeEmail(),cancelCourse.getTrainerEmail());
        adminService.sendCancelEmail(cancelCourse.getTrainerEmail(),cancelCourse.getCourseCode());


    }

    @Override
    public CourseApplication viewCourseApplication(ViewCourseApplicationRequest viewCourse) {
        Trainee foundTrainee = traineeRepository.findByEmail(viewCourse.getTraineeEmail());
        if(!userExist(viewCourse.getTraineeEmail()))throw new UserExistException("User doesn't exist");
        if(!foundTrainee.isLoginStatus())throw new AppLockedException("Kindly Login");
        CourseApplication course = applicationService.findCourse(viewCourse.getCourseCode(),viewCourse.getTraineeEmail());
        if(course == null)throw new CourseApplicationException("invalid details");
        return course;
    }

    @Override
    public void updateProfile(TraineeUpdateProfileRequest updateDetailRequest) {
     Trainee trainee = traineeRepository.findByEmail(updateDetailRequest.getEmail());
     if (!userExist(updateDetailRequest.getEmail())) throw new UserExistException(updateDetailRequest.getEmail() + " does not exist");
     if (!(updateDetailRequest.getEmail() == null)) trainee.setEmail(updateDetailRequest.getUpdatedEmail());
     if (!(updateDetailRequest.getPassword() == null)) trainee.setPassword(updateDetailRequest.getPassword());
     if (!(updateDetailRequest.getPhoneNumber() == null)) trainee.setPhoneNumber(updateDetailRequest.getPhoneNumber());
     if (!(updateDetailRequest.getAddress() == null)) trainee.setAddress(updateDetailRequest.getAddress());
     if (!(updateDetailRequest.getBioData() == null)) trainee.setBioData(updateDetailRequest.getBioData());
     if (!(updateDetailRequest.getFirstName() == null)) trainee.setFirstName(updateDetailRequest.getFirstName());
     if (!(updateDetailRequest.getLastName() == null)) trainee.setLastName(updateDetailRequest.getLastName());
     traineeRepository.save(trainee);
    }

    @Override
    public void logout(String email) {
        Trainee trainee = traineeRepository.findByEmail(email);
        if (!(userExist(email))) throw new UserExistException(email + " does not exist!!!");
        if (userExist(email)) trainee.setLoginStatus(false);
        traineeRepository.save(trainee);
    }

    @Override
    public Trainee findTrainee(String traineeEmail) {
        Trainee trainee = traineeRepository.findByEmail(traineeEmail);
        if(trainee == null)throw new TrainingException("Trainee doesn't exist");
        return trainee;
    }

    private boolean userExist(String email) {
        Trainee foundTrainee = traineeRepository.findByEmail(email);
        return foundTrainee != null;
    }
}
