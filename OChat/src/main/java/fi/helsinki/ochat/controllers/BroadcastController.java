package fi.helsinki.ochat.controllers;

import fi.helsinki.ochat.dbservices.MessageService;
import fi.helsinki.ochat.dbservices.UserService;
import fi.helsinki.ochat.models.Message;
import fi.helsinki.ochat.models.SendMessageBody;
import fi.helsinki.ochat.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BroadcastController {

    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @GetMapping("/broadcast")
    private List<Message> getMessagesWith(){
        return messageService.getAllBroadcastMessages();
    }

    @PostMapping("/broadcast")
    private ResponseEntity sendBroadcastMessage(@AuthenticationPrincipal OAuth2User principal, @RequestBody SendMessageBody body) {
        Message message = new Message();

        User sender = userService.getUserById(principal.getAttribute("sub"));
        message.setBroadcast(true);
        message.setText(body.getText());
        message.assignToSender(sender);
        messageService.sendMessage(message);
        return new ResponseEntity("message sent", HttpStatus.OK);
    }

}