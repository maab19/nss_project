package com.example;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String senderName;
    String receiverName;
    String text;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String salary) {
        this.text = salary;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}