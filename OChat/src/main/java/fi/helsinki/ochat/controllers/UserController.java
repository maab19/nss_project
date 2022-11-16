package fi.helsinki.ochat.controllers;

import fi.helsinki.ochat.dbservices.UserService;
import fi.helsinki.ochat.models.ChatWithName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("preferred_username"));
    }

    /*
    @PostMapping("/user")
    private boolean register(@AuthenticationPrincipal OAuth2User principal) {
        User newUser = new User();
        newUser.setName(principal.getAttribute("preferred_username"));
        userService.register(newUser);
        return true;
    }

    @GetMapping("/users")
    public ArrayList<User> allUsers() {
        return new ArrayList<User>(userService.getAllUsers());
    }
     */

    @GetMapping("/user/chats")
    public ResponseEntity<List<ChatWithName>> allChatsOfUsers(@AuthenticationPrincipal OAuth2User principal) throws Exception{
        return new ResponseEntity<List<ChatWithName>>(userService.getAllChatsOfUser(principal.getAttribute("sub")), HttpStatus.OK);
    }
}
