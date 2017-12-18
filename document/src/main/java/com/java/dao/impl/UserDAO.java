package com.java.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.dao.IUserDAO;
import com.java.model.User;

@Stateless
public class UserDAO extends GenericDAO<User> implements IUserDAO{

}
