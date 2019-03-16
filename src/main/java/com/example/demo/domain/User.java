package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "age", nullable = false)
	private int age;
	@Column(name = "username", nullable = false)
	private String userName;
	@Column(name = "userid", nullable = false)
	private String userId;

	public User(int age, String userName, String userId) {
		this.age = age;
		this.userName = userName;
		this.userId = userId;
	}

	public User() {

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
