package com.example.haako.practiceapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by haako on 07.09.2016.
 */
public class Users implements Serializable {

    private ArrayList<User> users;

    public Users() {
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() { return users; }
    public void addUser(User user) { users.add(user); }
    public void deleteUser(User user) { users.remove(user); }
    public User findUser(String username) {
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

}
