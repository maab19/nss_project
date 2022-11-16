package fi.helsinki.ochat.repos;

import fi.helsinki.ochat.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository < Message, Integer > {

}