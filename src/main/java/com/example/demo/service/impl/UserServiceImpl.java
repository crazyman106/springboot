package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public void addUser(User user) {
		// String sql = "insert into user(id,username, userid,age) values(?,?,?,?)";
		// mJdbcTemplate.update(sql, user.getId(), user.getUserName(), user.getUserId(),
		// user.getAge());
		userDao.saveUser2DB(user);
	}
}
