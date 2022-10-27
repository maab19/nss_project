package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Column(name="username", insertable=true, updatable=true, unique=true, nullable=false)
    String name;


    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
