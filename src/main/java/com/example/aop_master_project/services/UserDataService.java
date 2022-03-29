package com.example.aop_master_project.services;

import com.example.aop_master_project.model.dto.AuthenticateRequest;
import com.example.aop_master_project.model.entities.UserData;
import com.example.aop_master_project.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDataService {

    private UserDataRepository userRepository;

    public UserDataService(UserDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean usernameExists(String username) {
        return userRepository.usernameExists(username);
    }

    public void saveUser(AuthenticateRequest authReq) {
        UserData userData = new UserData(UUID.randomUUID().toString(), authReq.getUsername(), authReq.getPassword());
        userRepository.save(userData);
    }

    public boolean userWithPasswordExists(AuthenticateRequest authenticateRequest) {
        UserData userData = userRepository.userDataForUsername(authenticateRequest.getUsername());

        if(userData == null) {
            return false;
        }

        return userData.getUsername().compareToIgnoreCase(authenticateRequest.getUsername()) == 0 &&
                userData.getPassword().compareTo(authenticateRequest.getPassword()) == 0;
    }

}
