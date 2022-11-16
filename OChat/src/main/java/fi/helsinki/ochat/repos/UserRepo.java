package fi.helsinki.ochat.repos;

import fi.helsinki.ochat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {
    List<User> findByName(String name);
}
