package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.User;

public interface UserService {

	void addUser(User user);

	List<User> getUsers();
}
