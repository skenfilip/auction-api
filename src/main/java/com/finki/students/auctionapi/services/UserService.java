package com.finki.students.auctionapi.services;

import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.domain.UserRequest;
import com.finki.students.auctionapi.exceptions.UsernameAlreadyExistsException;
import com.finki.students.auctionapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User saveOrUpdateUser(UserRequest userRequest) {
        try {
            User user = new User(
                    0L,
                    userRequest.getUsername(),
                    userRequest.getFullName(),
                    passwordEncoder.encode(userRequest.getPassword()),
                    userRequest.getPhoneNumber(),
                    userRequest.getHomeAddress(),
                    null,
                    null,
                    new Date(),
                    null);

            return userRepository.save(user);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + userRequest.getUsername() + "' already exists");
        }

    }

}
