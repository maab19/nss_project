package fi.helsinki.ochat.dbservices;

import fi.helsinki.ochat.models.Message;
import fi.helsinki.ochat.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public void sendMessage(Message message) {
        messageRepo.saveAndFlush(message);
    }

    public List<Message> getAllBroadcastMessages(){
        List<Message> messages = messageRepo.findAll().stream()
                .filter(message -> message.isBroadcast())
                .collect(Collectors.toList());
        return messages;
    }

}