package com.laborator.repository;

import com.laborator.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<User> allUsers;
    private static UserRepository userRepository = null;

    protected UserRepository() {
        allUsers = new ArrayList<>();
        allUsers.add(new User("georgiana", "geo"));
        allUsers.add(new User("ciprian", "cip"));
    }

    public static UserRepository getInstance() {
        if (userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }

    public User getUserByUsername(String username) {
        for (User iterator : allUsers) {
            if (iterator.getUsername().equals(username))
                return iterator;
        }
        return null;
    }

    public boolean login(String username, String password) {
        User user = getUserByUsername(username);
        return user != null ? (user.getPassword().equals(password) ? true : false) : false;
    }
}
