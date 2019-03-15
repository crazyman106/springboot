package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

//dao层，写对数据库的操作

//交给springboot管理的注解
@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate mJdbcTemplate;

	public void saveUser2DB(User user) {
		String sql = "insert into user(id,username, userid,age) values(?,?,?,?)";
		mJdbcTemplate.update(sql, user.getId(), user.getUserName(), user.getUserId(), user.getAge());
	}

}
