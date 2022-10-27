package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List getAllUsers() {
        List users = new ArrayList();
        userRepo.findAll().forEach(message -> users.add(message));
        return users;
    }

    public void register(User user) {
        userRepo.save(user);
    }
}