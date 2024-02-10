package org.popaqConnect.controller;

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
    public ResponseEntity<?> register(@RequestBody ServiceProviderRegisterRequest providerRegisterRequest){
        ServiceProviderRegisterResponse providerRegisterResponse = new ServiceProviderRegisterResponse();
        try{
            providerServices.register(providerRegisterRequest);
            providerRegisterResponse.setMessage("Account created successful!!!");
            return new ResponseEntity<>(new ApiResponse(true, providerRegisterResponse), HttpStatus.CREATED);
        }
        catch (PopaqConnectException ex){
            providerRegisterResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, providerRegisterResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        try{
            providerServices.login(loginRequest);
            return "Login Successful";
        }
        catch (PopaqConnectException exception){
            return exception.getMessage();
        }
    }


    @PostMapping("/acceptClientRequest")
    public String acceptClientRequest(@RequestBody AcceptBookingRequest acceptBookingRequest){
        ServiceProviderCancelBookingRequestResponse cancelBookingRequestResponse = new ServiceProviderCancelBookingRequestResponse();
        try {
            providerServices.acceptClientBookRequest(acceptBookingRequest);
            return "booking accepted";
        }
        catch (PopaqConnectException exception){
            return exception.getMessage();
        }
    }


    @PostMapping("/cancelClientBookRequest")
    public ResponseEntity<?> cancelClientBookRequest(@RequestBody CancelBookingRequest cancelBookingRequest){
        ServiceProviderCancelBookingRequestResponse bookingRequestResponse = new ServiceProviderCancelBookingRequestResponse();
        try{
            providerServices.cancelClientBookRequest(cancelBookingRequest);
            bookingRequestResponse.setMessage("Booking request cancelled successful!!!");
            return new ResponseEntity<>(new ApiResponse(true, bookingRequestResponse), HttpStatus.GONE);
        }
        catch (PopaqConnectException exception){
            bookingRequestResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, bookingRequestResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateDetails")
    public ResponseEntity<?> updateDetails(@RequestBody UpdateProfileRequest updateProfileRequest){
        ServiceProviderUpdateRequestResponse providerUpdateRequest = new ServiceProviderUpdateRequestResponse();
        try {
            providerServices.updateDetails(updateProfileRequest);
            providerUpdateRequest.setMessage("account update successful!!! ");
            return new ResponseEntity<>(new ApiResponse(true, providerUpdateRequest), HttpStatus.GONE);
        }
        catch(PopaqConnectException exception){
            providerUpdateRequest.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, providerUpdateRequest), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findAllTrainees/{mail}")
    public ResponseEntity<?> findAllTrainees(@PathVariable("mail") String mail) {
        FindAllTraineesResponse findAllTraineesResponse = new FindAllTraineesResponse();
        try {
            findAllTraineesResponse.setAllTrainees(providerServices.findAllTrainees(mail));
            return new ResponseEntity<>(new ApiResponse(true,findAllTraineesResponse), HttpStatus.FOUND);
        }catch (PopaqConnectException popaqConnectException){
            findAllTraineesResponse.setAllTrainees(popaqConnectException.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,findAllTraineesResponse),HttpStatus.NOT_IMPLEMENTED);
        }
    }


}
