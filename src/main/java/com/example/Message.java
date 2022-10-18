package com.example;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String text;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String salary) {
        this.text = salary;
    }
}