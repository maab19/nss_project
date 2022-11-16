package fi.helsinki.ochat.repos;

import fi.helsinki.ochat.models.Chat;
import fi.helsinki.ochat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends  JpaRepository<Chat,Integer> {
    public List<Chat> findAllByUsers(User user);
}

