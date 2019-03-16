package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

//dao层，写对数据库的操作

//交给springboot管理的注解
@Repository
public class UserDao {

	@Autowired(required = true)
	JdbcTemplate mJdbcTemplate;

	public void saveUser2DB(User user) {
		String sql = "insert into user(id,username, userid,age) values(?,?,?,?)";
		mJdbcTemplate.update(sql, user.getId(), user.getUserName(), user.getUserId(), user.getAge());
	}

	public List<User> getUser() {
		String sql = "select * from user";
		return (List<User>) mJdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> users = new ArrayList<>();
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt(1));
					user.setUserId(rs.getString(2));
					user.setUserName(rs.getString(3));
					user.setAge(rs.getInt(4));
					users.add(user);
				}
				return users;
			}
		});
	}

}
