package com.java.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.dao.IDocumentDAO;
import com.java.model.Document;
@Stateless
public class DocumentDAO extends GenericDAO<Document> implements IDocumentDAO{

}
