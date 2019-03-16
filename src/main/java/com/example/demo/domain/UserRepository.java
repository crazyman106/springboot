package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

	User findByUserNameAndAge(String userName, Integer age);

	@Query("from user u where u.userName=:userName")
	User findUser(@Param("userName") String userName);
}
