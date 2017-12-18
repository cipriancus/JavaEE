package com.java.model;

import javax.persistence.*;

@Entity
@Cacheable
@Table(name = "Users")
public class User extends BaseEntity{
	@Column(name="USERNAME")
	private String username;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;
    
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLES_ID", unique = true, nullable = true, insertable = false, updatable = false)
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
