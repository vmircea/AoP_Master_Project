package com.example.aop_master_project.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.aop_master_project.model.dto.AuthenticateRequest;
import com.example.aop_master_project.repositories.UserDataRepository;
import com.example.aop_master_project.services.UserDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Value("${auth.secret_key}")
    private String secretKey;

    private UserDataService userService;

    public AuthenticationController(UserDataService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void Signup(@RequestBody AuthenticateRequest user)
    {
        if(userService.usernameExists(user.getUsername()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already exists");
        }

        if(user.getPassword().length() < 4)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password must have at least 4 characters");
        }

        userService.saveUser(user);
    }

    @PostMapping("/login")
    public String Login(@RequestBody AuthenticateRequest user)
    {
        if(!userService.userWithPasswordExists(user)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is wrong");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withClaim("Name", user.getUsername())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}