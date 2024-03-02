package com.main.office.Service;

import com.main.office.Repo.UserRepository;
import com.main.office.Мodel.User;

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
            return false;
        }

        try {
            repo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
