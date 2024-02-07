package com.main.office.Service;

import com.main.office.Repo.UserRepository;
import com.main.office.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepository repo;

    @Transactional
    public boolean register(User user) {
        if (repo.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
            // User with the same username or email already exists
            return false;
        }

        try {
            repo.save(user);
            return true; // Registration successful
        } catch (Exception e) {
            // Handle the exception or log it
            return false; // Registration failed
        }
    }
}
