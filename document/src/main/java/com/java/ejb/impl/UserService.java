package com.java.ejb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.java.dao.IUserDAO;
import com.java.dao.impl.UserDAO;
import com.java.ejb.IUserService;
import com.java.model.User;

@Stateless
public class UserService implements IUserService{
	@EJB
	IUserDAO userDAO;

	@Override
	public boolean addUser(User user) {
		userDAO.persist(user);
		return true;
	}
}
