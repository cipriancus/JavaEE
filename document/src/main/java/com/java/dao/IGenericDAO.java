package com.java.dao;

import java.util.List;

import com.java.model.BaseEntity;
import javax.ejb.Remote;

@Remote
public interface IGenericDAO<T extends BaseEntity> {

	T getById(Long id);

	T getByCode(String code);

	List<T> getAll();

	void delete(T value);

	void persist(T entity);
}