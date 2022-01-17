package com.example.kubeioauth.services;

import com.example.kubeioauth.models.User;
import com.example.kubeioauth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public boolean isRegisteredUser(final User user) {
        Optional<User> registeredUser = userRepository.findById(user.getUserName());
        if(registeredUser.isPresent() && registeredUser.get().getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}