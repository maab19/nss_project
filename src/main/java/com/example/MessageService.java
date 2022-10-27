package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public List getAllMessages() {
        List messages = new ArrayList();
        messageRepo.findAll().forEach(message -> messages.add(message));
        return messages;
    }

    public List getAllMessagesBetween(String user1, String user2) {
        List messages = new ArrayList();
        messageRepo.findAll().forEach(message ->
        {
            if (message.getSenderName() != null&&message.getReceiverName()!=null
                    &&((message.getSenderName().equals(user1) && message.getReceiverName().equals(user2))
                    || (message.getSenderName().equals(user2) && message.getReceiverName().equals(user1)))){
                messages.add(message);
            }
        });
        return messages;
    }

    public List getAllBroadcastMessages(){
        List messages = new ArrayList();
        messageRepo.findAll().forEach(message ->
        {
            if (message.getReceiverName()==null){
                messages.add(message);
            }
        });
        return messages;
    }

    public Message getMessageById(int id) {
        return messageRepo.findById(id).get();
    }

    public void saveOrUpdate(Message message) {
        messageRepo.save(message);
    }

    public void delete(int id) {
        messageRepo.deleteById(id);
    }
}