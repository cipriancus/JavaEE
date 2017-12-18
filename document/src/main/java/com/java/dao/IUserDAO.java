package com.java.dao;

import com.java.model.User;

import javax.ejb.Remote;
@Remote
public interface  IUserDAO extends IGenericDAO<User>{
    public User getUserByUsername(String username);
}
