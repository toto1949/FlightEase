package com.taoufiq.authentication.service;

import com.taoufiq.authentication.entities.User;
import com.taoufiq.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null ||
                userRepository.findByEmail(user.getEmail()) != null) {
            return null;
        }
        user.setPassword(user.getPassword());

        // Assign default role or roles if needed
        // Set<String> roles = new HashSet<>(Arrays.asList("ROLE_USER"));
        // user.setRoles(roles);

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

