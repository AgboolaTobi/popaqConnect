package org.popaqConnect.controllers;

import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.*;
import org.popaqConnect.exceptions.PopaqConnectException;
import org.popaqConnect.services.serviceProvider.ServiceProviderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ServiceProviderController {
    @Autowired
    private ServiceProviderServices providerServices;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ServiceProviderRegisterRequest providerRegisterRequest) {
        ServiceProviderRegisterResponse providerRegisterResponse = new ServiceProviderRegisterResponse();
        try {
            providerServices.register(providerRegisterRequest);
            providerRegisterResponse.setMessage("Account created successful!!!");
            return new ResponseEntity<>(new ApiResponse(true, providerRegisterResponse), HttpStatus.CREATED);
        } catch (PopaqConnectException ex) {
            providerRegisterResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, providerRegisterResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ServiceProviderLoginResponse providerLoginResponse = new ServiceProviderLoginResponse();
        try {
            providerServices.login(loginRequest);
            providerLoginResponse.setMessage("Login Successful");
            return new ResponseEntity<>(new ApiResponse(true, providerLoginResponse), HttpStatus.GONE);
        } catch (PopaqConnectException exception) {
            providerLoginResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, providerLoginResponse), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/acceptClientRequest")
    public String acceptClientRequest(@RequestBody AcceptBookingRequest acceptBookingRequest) {

        try {
            providerServices.acceptClientBookRequest(acceptBookingRequest);
            return "booking accepted";
        } catch (PopaqConnectException exception) {
            return exception.getMessage();
        }
    }


    @PostMapping("/cancelClientBookRequest")
    public ResponseEntity<?> cancelClientBookRequest(@RequestBody CancelBookingRequest cancelBookingRequest) {
        ServiceProviderCancelBookingRequestResponse bookingRequestResponse = new ServiceProviderCancelBookingRequestResponse();
        try {
            providerServices.cancelClientBookRequest(cancelBookingRequest);
            bookingRequestResponse.setMessage("Booking request cancelled successful!!!");
            return new ResponseEntity<>(new ApiResponse(true, bookingRequestResponse), HttpStatus.GONE);
        } catch (PopaqConnectException exception) {
            bookingRequestResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, bookingRequestResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateDetails")
    public ResponseEntity<?> updateDetails(@RequestBody UpdateProfileRequest updateProfileRequest) {
        ServiceProviderUpdateResponse providerUpdateResponse = new ServiceProviderUpdateResponse();
        try {
            providerServices.updateDetails(updateProfileRequest);
            providerUpdateResponse.setMessage("account update successful!!! ");
            return new ResponseEntity<>(new ApiResponse(true, providerUpdateResponse), HttpStatus.GONE);
        } catch (PopaqConnectException exception) {
            providerUpdateResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, providerUpdateResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/completeJobStatus")
    public ResponseEntity<?> completeJobStatus(@RequestBody CompleteJobRequest completeJobRequest) {
        ServiceProviderCompleteJobResponse providerCompleteJobResponse = new ServiceProviderCompleteJobResponse();
        try {
            providerServices.completeJobStatus(completeJobRequest);
            providerCompleteJobResponse.setMessage("job completed");
            return new ResponseEntity<>(new ApiResponse(true, providerCompleteJobResponse), HttpStatus.OK);
        } catch (PopaqConnectException exception) {
            providerCompleteJobResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, providerCompleteJobResponse), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/cancelTrainingRequest")
    public ResponseEntity<?> cancelTrainingRequest(@RequestBody CancelServiceProviderRequest cancelServiceProviderRequest) {
        ServiceProviderCancelTraineeRequest providerCancelTraineeRequest = new ServiceProviderCancelTraineeRequest();
        try {
            providerServices.cancelTrainingRequest(cancelServiceProviderRequest);
            providerCancelTraineeRequest.setMessage("request Cancelled successful!!!");
            return new ResponseEntity<>(new ApiResponse(true, providerCancelTraineeRequest), HttpStatus.GONE);
        } catch (PopaqConnectException exception) {
            providerCancelTraineeRequest.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(true, providerCancelTraineeRequest), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam String serviceProviderEmail) {

        try {
            providerServices.deleteAccount(serviceProviderEmail);
            return "account deleted successfully";
        }
        catch (PopaqConnectException exception){
            return exception.getMessage();
        }
    }
}


