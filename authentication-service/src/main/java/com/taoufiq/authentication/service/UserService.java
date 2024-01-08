package com.taoufiq.authentication.service;

import com.taoufiq.authentication.entities.User;
import com.taoufiq.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null ||
                userRepository.findByEmail(user.getEmail()) != null) {
            return null;
        }
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

