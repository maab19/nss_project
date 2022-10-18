package com.example;

import nonapi.io.github.classgraph.concurrency.SingletonMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/messages")
    private List getAllMessages() {
        System.out.println("aAAAAAAAAAAAAAAAAA");
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    private Message getMessageById(@PathVariable("id") int id) {
        return messageService.getMessageById(id);
    }

    @PostMapping("/messages")
    private ResponseEntity sendMessage(@AuthenticationPrincipal OAuth2User principal, @RequestBody Message message) {
        System.out.println(message);
        try {
            message.setName(principal.getAttribute("login"));
            messageService.saveOrUpdate(message);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Sent message: " + message, HttpStatus.CREATED);
    }

    @DeleteMapping("/messages/{id}")
    private ResponseEntity deleteById(@PathVariable("id") int id) {
        try {
            messageService.delete(id);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("deleted message with id: " + id, HttpStatus.OK);
    }
}