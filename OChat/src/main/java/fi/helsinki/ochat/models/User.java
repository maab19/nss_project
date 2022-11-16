package fi.helsinki.ochat.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat_user")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @NonNull String id;
    @Column(name="user_name")
    @NonNull String name;
    @ManyToMany(mappedBy = "users")
    @JsonBackReference
    Set<Chat> chats;
    @OneToMany(mappedBy = "sender")
    @JsonBackReference
    List<Message> messages;
}
