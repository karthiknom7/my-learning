package com.modle;

public class User {
    private String username;
    private Integer id;

    public User(Integer id, String username) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }
}
