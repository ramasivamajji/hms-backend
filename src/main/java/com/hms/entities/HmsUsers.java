package com.hms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hms_users", schema = "public")
public class HmsUsers {
	
	@Column(name = "user_name")
	private String user_name;
	
	@Id
	@Column(name = "user_email")
	private String user_email;
	
	@Column(name = "user_password")
	private String user_password;
	

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	

}
