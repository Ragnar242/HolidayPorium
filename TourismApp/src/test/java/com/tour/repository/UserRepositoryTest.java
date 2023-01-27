package com.tour.repository;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tour.entity.UserEntity;

@DataJpaTest
public class UserRepositoryTest {

		@Autowired
		UserRepository userRepository;

		private UserEntity user;

		@BeforeEach
		public void setUp() {
			user = new UserEntity();
			user.setEmailId("fahad@gmail.com");
			user.setPassword("Fahad@123");
			user.setUserId(1234);
			user.setUserName("Fahad rahman");
			user.setContactNumber("9988776655");
		}

		// invalid login
		@Test
		public void invalidLogin() throws Exception {
			assertNull(userRepository.findByContactNumber("1234567890"));
		}
}
