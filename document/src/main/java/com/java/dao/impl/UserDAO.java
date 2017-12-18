package com.java.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.java.dao.IUserDAO;
import com.java.model.User;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Stateless
public class UserDAO extends GenericDAO<User> implements IUserDAO{

    public User getUserByUsername(String username){
        Query query = entityManager.createQuery("from User u where u.username=:user_name");
        query.setParameter("user_name", username);

        @SuppressWarnings("rawtypes")
        List list = query.getResultList();

        if (list.isEmpty()) {
            return null;
        }
        return (User)list.get(0);
    }
}
