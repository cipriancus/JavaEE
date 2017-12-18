package com.java.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Documents")
public class Document extends BaseEntity{
		
	@OneToOne
	@JoinColumn(name="USERS_ID", unique = true, nullable = true, insertable = false, updatable = false)
	private User user;
	
	@Column(name="MIME_TYPE")
	private String mimeType;
	
	@Column(name="DATA")
	private Blob data;
}
