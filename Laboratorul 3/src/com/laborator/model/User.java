package com.laborator.model;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;

    public User(String username,String password){
        this.id= UUID.randomUUID().toString();
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
