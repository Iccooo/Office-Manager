package com.main.office.Service;

import com.main.office.Repo.UserRepository;
import com.main.office.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private UserRepository repo;

    public User login(String username, String email,  String password) {
        return repo.findByUsernameAndPassword(username, password);
    }


}