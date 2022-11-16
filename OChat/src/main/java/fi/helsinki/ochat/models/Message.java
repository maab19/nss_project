package fi.helsinki.ochat.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @Column(name="is_broadcast")
    private boolean isBroadcast;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = true)
    @JsonManagedReference
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonManagedReference
    private Chat chat;

    public void assignToChat(Chat chat) {
        chat.getMessages().add(this);
        this.setChat(chat);
    }
    public void assignToSender(User sender) {
        sender.getMessages().add(this);
        this.setSender(sender);
    }
}