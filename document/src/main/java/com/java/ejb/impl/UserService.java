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

	@Override
	public User login(String username, String password){
		User user=userDAO.getUserByUsername(username);
		if(user!=null&&user.getPassword().equals(password)){
			return user;
		}
		return null;
	}
}
