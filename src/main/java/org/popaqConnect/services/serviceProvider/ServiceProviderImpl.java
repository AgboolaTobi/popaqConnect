package org.popaqConnect.services.serviceProvider;

import org.popaqConnect.data.models.*;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.exceptions.*;
import org.popaqConnect.services.Admin.AdminService;
import org.popaqConnect.services.Booking.BookServices;
import org.popaqConnect.services.CourseApplication.CourseApplicationService;
import org.popaqConnect.services.job.JobService;
import org.popaqConnect.utils.ServiceProviderMapper;
import org.popaqConnect.utils.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProviderImpl implements ServiceProviderServices {
    @Autowired
    ServiceProviderRepository providerRepository;
    @Autowired
    BookServices bookServices;
    @Autowired
    AdminService adminService;
    @Autowired
    CourseApplicationService courseApplicationService;

    @Autowired
    JobService jobService;
    @Override
    public void register(ServiceProviderRegisterRequest registerRequest) {
        if(findUser(registerRequest.getEmail()).isPresent()) throw new UserExistException(registerRequest.getEmail() + " already exist!!!");
        if (!Verification.verifyEmail(registerRequest.getEmail())) throw new InvalidDetailsException("Invalid email format!!");
        if(!Verification.verifyPassword(registerRequest.getPassword())) throw new InvalidDetailsException("Invalid password format!!!");
        if (!Verification.verifyPhoneNumber(registerRequest.getPhoneNumber())) throw new InvalidDetailsException("Invalid phone number");
        ServiceProvider newServiceProvider = ServiceProviderMapper.serviceProviderMap(registerRequest);
        Job job = jobService.save(registerRequest.getCategory(), registerRequest.getJobTitle());
        newServiceProvider.setJob(job);
        providerRepository.save(newServiceProvider);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional <ServiceProvider> serviceProvider = findUser(loginRequest.getEmail());
        if (serviceProvider.isEmpty()) throw new InvalidLoginException(loginRequest.getEmail() + " does not exist");
        if (!serviceProvider.get().getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailsException("Invalid Login Details!!");
        serviceProvider.get().setLoginStatus(true);
        providerRepository.save(serviceProvider.get());
    }

    private boolean isLocked(String email){
        Optional<ServiceProvider> user = findUser(email);
        if(user.get().isAvailable()) return true;
        return false;
    }

    @Override
    public Optional<ServiceProvider> findUser(String serviceProviderEmail) {
        return providerRepository.findByEmail(serviceProviderEmail);
    }


    @Override
    public void acceptClientBookRequest(AcceptBookingRequest bookingRequest) {
        Optional <ServiceProvider> serviceProvider = providerRepository.findByEmail(bookingRequest.getEmail());
        userExist(bookingRequest.getEmail());
        if (!isLocked(bookingRequest.getEmail()))throw new AppLockedException("Kindly login");
        bookServices.setBookType(serviceProvider.get().getEmail(),bookingRequest);
        serviceProvider.get().setAvailable(false);
        providerRepository.save(serviceProvider.get());
        Book book = bookServices.findABookingRequest(bookingRequest.getId() ,serviceProvider.get().getEmail());
        adminService.sendAcceptRequest(bookingRequest.getId(),book.getClientEmail());
    }

    @Override
    public List<ServiceProvider> findAllServiceProviderAvailableForTraining() {
        List<ServiceProvider> serviceProviders = new ArrayList<>();
        for(ServiceProvider serviceProvider: providerRepository.findAll())
            if(serviceProvider.isAvailableForTraining())serviceProviders.add(serviceProvider);
        return serviceProviders;
    }

    @Override
    public CourseApplication viewACourseApplication(ViewTraineeCourseRequest courseRequest) {
       userExist(courseRequest.getTrainerEmail());
       if(!findUser(courseRequest.getTrainerEmail()).get().isLoginStatus())throw new AppLockedException("Kindly login");
       return courseApplicationService.findCourse(courseRequest.getCourseCode(),courseRequest.getTrainerEmail());
    }

    @Override
    public void updateOnTrainee(UpdateOnCourseApplicationRequest updateCourseRequest) {
        userExist(updateCourseRequest.getTrainerEmail());
        if(!findUser(updateCourseRequest.getTrainerEmail()).get().isLoginStatus())throw new AppLockedException("Kindly login");
        courseApplicationService.updateCourseApplication(updateCourseRequest);
    }

    @Override
    public List<Book> findAllBookingHistory(String email) {
        userExist(email);
        if(!findUser(email).get().isLoginStatus())throw new AppLockedException("Kindly login");
        return bookServices.findAllBookingRequest(email);
    }

    @Override
    public Book viewABookingHistory(ViewABookingRequest viewABookingRequest) {
        userExist(viewABookingRequest.getEmail());
        if(!findUser(viewABookingRequest.getEmail()).get().isLoginStatus())throw new AppLockedException("Kindly login");
        Book booking = bookServices.findABookingRequest(viewABookingRequest.getBookingId(), viewABookingRequest.getEmail());
        if(booking == null) throw new BookingRequestException("Invalid Details");
        return booking;
    }

    @Override
    public void removeUser(String traineeEmail, String trainerEmail) {
        Optional<ServiceProvider> serviceProvider = findUser(trainerEmail);
        userExist(trainerEmail);
        if(!serviceProvider.get().isLoginStatus())throw new AppLockedException("Kindly login");
        List<Trainee> trainees = serviceProvider.get().getTrainees();
        List<Trainee> copyTrainees = new ArrayList<>(trainees);
        for(Trainee trainee: copyTrainees){
            if(trainee.getEmail().equals(traineeEmail))trainees.remove(trainee);
        }
        serviceProvider.get().setTrainees(trainees);
        providerRepository.save(serviceProvider.get());
    }

    @Override
    public void cancelRequest(CancelServiceProviderRequest cancelRequest) {
        Optional<ServiceProvider> serviceProvider = findUser(cancelRequest.getServiceProviderEmail());
        userExist(cancelRequest.getServiceProviderEmail());
        if(!serviceProvider.get().isLoginStatus())throw new AppLockedException("Kindly login");
        if(cancelRequest.getId().startsWith("TR-")){
            courseApplicationService.cancelCourse(cancelRequest.getId(), cancelRequest.getEmail());
            removeUser(cancelRequest.getEmail() ,cancelRequest.getServiceProviderEmail());}


    }

    @Override
    public void completeJobStatus(CompleteJobRequest completeJobRequest) {
        Optional <ServiceProvider> serviceProvider = providerRepository.findByEmail(completeJobRequest.getEmail());
        if (serviceProvider.isEmpty()) throw new UserExistException(completeJobRequest.getEmail()+" does not exist!!!");
        bookServices.completeJobStatus(completeJobRequest);
        providerRepository.save(serviceProvider.get());
    }

    @Override
    public void cancelClientBookRequest(CancelBookingRequest cancelBookingRequest) {
        Optional<ServiceProvider> serviceProvider = providerRepository.findByEmail(cancelBookingRequest.getEmail());
        if(serviceProvider.isEmpty())throw new UserExistException(cancelBookingRequest.getEmail() +" doesn't exist");
        bookServices.cancelBookRequest(cancelBookingRequest);
        serviceProvider.get().setAvailable(true);
        providerRepository.save(serviceProvider.get());
    }

    @Override
    public void logout(String email) {
        Optional <ServiceProvider> serviceProvider = providerRepository.findByEmail(email);
        if (serviceProvider.isEmpty()) throw new InvalidDetailsException("Invalid details please try again !!!");
        serviceProvider.get().setLoginStatus(false);
        providerRepository.save(serviceProvider.get());
    }

    @Override
    public void updateDetails(UpdateProfileRequest updateProfileRequest) {
        Optional <ServiceProvider> serviceProvider = providerRepository.findByEmail(updateProfileRequest.getPreviousEmail());
        userExist(updateProfileRequest.getPreviousEmail());
        if (serviceProvider.isEmpty()) throw new InvalidDetailsException("User Does not Exist!!!");
        if (!(updateProfileRequest.getUpdatedEmail() == null)){serviceProvider.get().setEmail(updateProfileRequest.getUpdatedEmail());}
        if (!(updateProfileRequest.getPassword() == null)){serviceProvider.get().setPassword(updateProfileRequest.getPassword());}
        if (!(updateProfileRequest.getPhoneNumber() ==  null)){serviceProvider.get().setPhoneNumber(updateProfileRequest.getPhoneNumber());}
        if (!(updateProfileRequest.getAddress() == null)){serviceProvider.get().setAddress(updateProfileRequest.getAddress());}
        if (!(updateProfileRequest.getBioData() == null)){serviceProvider.get().setBioData(updateProfileRequest.getBioData());}
        if (!(updateProfileRequest.getJob() == null)) serviceProvider.get().setJob(updateProfileRequest.getJob());
        if (!(updateProfileRequest.getUsername() == null)){ serviceProvider.get().setUserName(updateProfileRequest.getUsername());}
//        if (updateProfileRequest.isAvailableForTraining())updateProfileRequest.isAvailableForTraining()){ serviceProvider.get().setChargePerHour(updateProfileRequest.getChargePerHour());}
        if (serviceProvider.get().isAvailableForTraining() != updateProfileRequest.isAvailableForTraining()){ serviceProvider.get().setAvailableForTraining(updateProfileRequest.isAvailableForTraining());}
        providerRepository.save(serviceProvider.get());
    }

    @Override
    public void deleteAccount(String email) {
        Optional <ServiceProvider> serviceProvider = providerRepository.findByEmail(email);
        if (serviceProvider.isEmpty()) throw new InvalidDetailsException(email + " does not exist!!!");
        userExist(email);
        providerRepository.delete(serviceProvider.get());
        serviceProvider.get().setLoginStatus(false);
        providerRepository.save(serviceProvider.get());
    }


    private void userExist(String email){
        Optional <ServiceProvider> serviceProvider = providerRepository.findByEmail(email);
       if(serviceProvider.isEmpty())throw new  UserExistException("User doesn't exist");
    }
}
