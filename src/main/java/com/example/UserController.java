package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("login"));
    }

    @PostMapping("/user")
    private boolean register(@AuthenticationPrincipal OAuth2User principal) {
        User newUser = new User();
        newUser.setName(principal.getAttribute("login"));
        userService.register(newUser);
        return true;
    }

    @GetMapping("/users")
    public ArrayList<User> allUsers() {
        return new ArrayList<User>(userService.getAllUsers());
    }
}
