package com.java.session;

import com.java.model.User;

import javax.ejb.Stateful;

@Stateful
public class SessionData {
    private User loggedUser;

    private String sessionId;

    private boolean isAdmin;

    public boolean isLogged() {

        boolean isLogged = loggedUser != null ? true : false;

        return isLogged;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;

        isAdmin = loggedUser.getRole().getRoleType().equals("Admin") ? true : false;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
