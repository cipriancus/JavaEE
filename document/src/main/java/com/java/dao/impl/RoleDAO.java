package com.java.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.dao.IRoleDAO;
import com.java.dao.IUserDAO;
import com.java.model.Role;

@Stateless
public class RoleDAO extends GenericDAO<Role> implements IRoleDAO{
	
}