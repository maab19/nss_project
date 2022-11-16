package fi.helsinki.ochat.controllers;

import fi.helsinki.ochat.dbservices.ChatService;
import fi.helsinki.ochat.dbservices.MessageService;
import fi.helsinki.ochat.dbservices.UserService;
import fi.helsinki.ochat.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

    @PostMapping("chat")
    private ResponseEntity newPersonalChat(@AuthenticationPrincipal OAuth2User principal, @RequestBody NewChatBody body){
        if(body.getUserName().isBlank()) {
            return new ResponseEntity("Empty username of chat partner given",HttpStatus.BAD_REQUEST);
        }
        List<User> users = userService.getUserByUserName(body.getUserName());
        if(body.getUserName().isBlank()) {
            return new ResponseEntity("no user found with given username",HttpStatus.BAD_REQUEST);
        }
        User user1 = users.get(0);
        User user2 = userService.getUserById(principal.getAttribute("sub"));
        if (user1.equals(user2)) {
            return new ResponseEntity("no chat with oneself possible",HttpStatus.BAD_REQUEST);
        }
        if (chatService.personalChatExists(user1, user2)) {
            return new ResponseEntity("personal chat already exists",HttpStatus.BAD_REQUEST);
        }
        Set<User> usersInChat = new HashSet<>();
        usersInChat.add(user1);
        usersInChat.add(user2);
        Chat chat = new Chat();
        chat.setMessages(new ArrayList<Message>());
        chat.setUsers(usersInChat);
        chatService.createChat(chat);
        return new ResponseEntity("created chat", HttpStatus.OK);
    }

    @GetMapping("chat/{id}/messages")
    public ResponseEntity<List<Message>> getAllChatMessages(@AuthenticationPrincipal OAuth2User principal, @PathVariable("id") Integer id) {
        User user = userService.getUserById(principal.getAttribute("sub"));
        Chat chat = chatService.getChatById(id);
        if(!chat.getUsers().contains(user)) {
            return new ResponseEntity("user not a chat member", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(chatService.getAllChatMessages(id),HttpStatus.OK);
    }
    @PostMapping("chat/{id}/message")
    public ResponseEntity sendMessageInChat(@AuthenticationPrincipal OAuth2User principal, @PathVariable("id") int id, @RequestBody SendMessageBody body) {
        User user = userService.getUserById(principal.getAttribute("sub"));
        Chat chat = chatService.getChatById(id);
        if(!chat.getUsers().contains(user)) {
            return new ResponseEntity("user not a chat member", HttpStatus.UNAUTHORIZED);
        }
        Message message = new Message();
        message.assignToChat(chat);
        message.setText(body.getText());
        message.assignToSender(user);
        message.setBroadcast(false);
        messageService.sendMessage(message);
        return new ResponseEntity("message sent", HttpStatus.OK);
    }
}
