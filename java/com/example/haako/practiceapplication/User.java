package com.example.haako.practiceapplication;

import java.io.Serializable;

/**
 * Created by haako on 07.09.2016.
 */
public class User implements Serializable {

    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
