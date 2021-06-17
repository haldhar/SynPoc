package com.order.orderdemo.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
@NamedQueries({
		@NamedQuery(name = UserDetails.NAMED_QUERY_EXECUTE_AUTHENTICATE, query = "select e from UserDetails e where e.userName=:userName and e.password=:password") })
public class UserDetails {

	public static final String NAMED_QUERY_EXECUTE_AUTHENTICATE = "UserDetails.authenticate";

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
