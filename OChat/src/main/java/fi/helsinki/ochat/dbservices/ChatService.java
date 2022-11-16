package fi.helsinki.ochat.dbservices;

import fi.helsinki.ochat.models.Chat;
import fi.helsinki.ochat.models.Message;
import fi.helsinki.ochat.models.User;
import fi.helsinki.ochat.repos.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatService {
    @Autowired
    ChatRepo chatRepo;

    public void createChat(Chat chat) {
        chatRepo.saveAndFlush(chat);
    }
    public Chat getChatById(int id) {
        return chatRepo.findById(id).get();
    }
    public List<Message> getAllChatMessages(Integer id) {
        Chat chat = chatRepo.findById(id).get();
        return chat.getMessages();
    }

    public boolean personalChatExists(User user1, User user2) {
        Set<Chat> chats = new HashSet<>();
        chats.addAll(chatRepo.findAllByUsers(user1));
        chats.retainAll(chatRepo.findAllByUsers(user2));
        if (chats.size() == 0) {
            return false;
        }
        return true;
    }
}
