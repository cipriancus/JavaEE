package com.java.ejb;

import javax.ejb.Remote;
import com.java.model.User;

@Remote
public interface IUserService {
	public boolean addUser(User user);
}
