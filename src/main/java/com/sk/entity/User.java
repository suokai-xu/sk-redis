package com.sk.entity;

import java.io.Serializable;

import javax.persistence.Table;

/**
*
*2019年3月12日上午11:09:48
*@HXing xu
*sk-redis
*User.java
*
**/

@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
	private String id;
	private String name;
	private String password;
	private String phone;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	 @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", name=" + name +
	                ", password=" + password  +
	                ", phone=" + phone + 
	                '}';
	    }

}
