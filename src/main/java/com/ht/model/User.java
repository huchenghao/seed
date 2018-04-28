package com.ht.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User extends BaseModel{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length=11)
	private String mobile;
	
	@Column(length=32)
	private String password;
	
	
	public User() {
		super();
	}

	public User(String mobile, String password) {
		super();
		this.mobile = mobile;
		this.password = password;
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
