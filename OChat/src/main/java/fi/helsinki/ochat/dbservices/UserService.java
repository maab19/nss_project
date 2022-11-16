package fi.helsinki.ochat.dbservices;

import fi.helsinki.ochat.models.Chat;
import fi.helsinki.ochat.models.ChatWithName;
import fi.helsinki.ochat.models.User;
import fi.helsinki.ochat.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        userRepo.findAll().forEach(message -> users.add(message));
        return users;
    }

    public List<User> getUserByUserName(String username) {
        List<User> users = userRepo.findByName(username);
        return users;
    }
    public User getUserById(String id) {
        return userRepo.findById(id).get();
    }
    public List<ChatWithName> getAllChatsOfUser(String userId) throws Exception{
        List<ChatWithName> chatsWithName = new ArrayList<>();
        String userName = getUserById(userId).getName();
        Set<Chat> chats = getUserById(userId).getChats();
        for (Chat chat: chats) {
            Set<User> chatUsers = chat.getUsers();
            if (chatUsers.size() != 2) {
                throw new Exception("Group chats are not implemented yet");
            }
            User chatPartner = chatUsers.stream().filter(user -> user.getName() != userName).findFirst().get();
            ChatWithName chatWithName = new ChatWithName(chat.getId(), chatPartner.getName());
            chatsWithName.add(chatWithName);
        }
        chatsWithName.sort(Comparator.comparing(ChatWithName::getName));
        return chatsWithName;
    }

    public void register(User user) {
        userRepo.saveAndFlush(user);
    }
}