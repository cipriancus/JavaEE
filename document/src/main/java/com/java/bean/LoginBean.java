package com.java.bean;

import com.java.ejb.IUserService;
import com.java.model.User;
import com.java.session.SessionData;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean {
    private String username;
    private String password;

    @EJB
    private IUserService userService;

    @EJB
    SessionData sessionData;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String login() {
        if (sessionData.isLogged() == false) {
            User user = userService.login(username, password);
            if (user != null) {
                sessionData.setLoggedUser(user);

                return sessionData.isAdmin() ? "allDocuments.xhtml?faces-redirect=true" : "myfiles.xhtml?faces-redirect=true";
            }
        }
        return "login.xhtml?faces-redirect=true";
    }
}
