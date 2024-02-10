package org.popaqConnect.controller;

import org.popaqConnect.dtos.requests.TraineeLoginRequest;
import org.popaqConnect.dtos.requests.TraineeRegistrationRequest;
import org.popaqConnect.dtos.response.ApiResponse;
import org.popaqConnect.dtos.response.TraineeLoginResponse;
import org.popaqConnect.dtos.response.TraineeRegistrationResponse;
import org.popaqConnect.exceptions.PopaqConnectException;
import org.popaqConnect.services.Trainee.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @PostMapping("registration")
    public ResponseEntity<?> registration(@RequestBody TraineeRegistrationRequest registrationRequest){
        TraineeRegistrationResponse registrationResponse = new TraineeRegistrationResponse();
        try {
            traineeService.register(registrationRequest);
            registrationResponse.setMessage("Congratulations "+ registrationRequest.getEmail()+ " Your registration is complete.");
            return new ResponseEntity<>(new ApiResponse(true,registrationResponse), HttpStatus.CREATED);
        }catch (PopaqConnectException exception){
            registrationResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,registrationResponse),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TraineeLoginRequest loginRequest){
        TraineeLoginResponse loginResponse = new TraineeLoginResponse();
        try {
            traineeService.login(loginRequest);
            loginResponse.setMessage("Welcome back,"+ loginRequest.getEmail()+ "! We're glad to see you again.");
            return new ResponseEntity<>(new ApiResponse(true,loginResponse),HttpStatus.ACCEPTED);
        }catch (PopaqConnectException exception){
            loginResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,loginResponse),HttpStatus.BAD_REQUEST);
        }
    }
}
