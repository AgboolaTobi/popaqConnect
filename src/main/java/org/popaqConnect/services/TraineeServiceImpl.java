package org.popaqConnect.services;

import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.repositories.TraineeRepository;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserDoesNotExistException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.popaqConnect.utils.Mapper.mapTrainee;


@Service
public class TraineeServiceImpl implements TraineeService{
    @Autowired
    private TraineeRepository traineeRepository;
    @Override
    public void register(RegisterRequest registerRequest) {
        if (userExist(registerRequest.getEmail())) throw new UserExistException(registerRequest.getEmail()+
                " Already Exist");
        if(!VerifyPassword.verifyPassword(registerRequest.getPassword()))
            throw new InvalidDetailsException("Wrong password format");
        if(!VerifyPassword.verifyEmail(registerRequest.getEmail()))
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
        foundTrainee.setLocked(false);
        traineeRepository.save(foundTrainee);
    }
    private boolean userExist(String email) {
        Trainee foundTrainee = traineeRepository.findByEmail(email);
        return foundTrainee != null;
    }
}
