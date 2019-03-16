package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDemo2ApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
		userRepository.save(new User(11, "AAA", "1"));
		userRepository.save(new User(13, "BBB", "1"));
		userRepository.save(new User(14, "CCC", "1"));
		userRepository.save(new User(15, "DDD", "1"));
		userRepository.save(new User(16, "EEE", "1"));
		userRepository.save(new User(17, "FFF", "1"));
		userRepository.save(new User(18, "GGG", "1"));
		userRepository.save(new User(19, "HHH", "1"));
		userRepository.save(new User(20, "III", "1"));
		userRepository.save(new User(21, "JJJ", "1"));

		// 测试findAll, 查询所有记录
		Assert.assertEquals(10, userRepository.findAll().size());

		// 测试findByName, 查询姓名为FFF的User
		Assert.assertEquals(17, userRepository.findByUserName("FFF").getAge());

		// 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
		Assert.assertEquals("FFF", userRepository.findByUserNameAndAge("FFF", 17).getUserName());

		// 测试删除姓名为AAA的User
		userRepository.delete(userRepository.findByUserName("AAA"));

		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
		Assert.assertEquals(9, userRepository.findAll().size());
	}

}
