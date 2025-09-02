package com.test.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.email.CustomMsg;
import com.test.email.service.IEmailService;
import com.test.model.UserEntity;
import com.test.repository.UserRepository;
import com.test.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IEmailService emailService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveUser(UserEntity userEntity) {

		Optional<UserEntity> temp = userRepo.findByEmail(userEntity.getEmail());
		if (temp.isPresent()) {
			throw new RuntimeException("User is Already Exist Exception");

		}
		userEntity.setRegisteredAt(LocalDate.now());
		userEntity.setRole("USER");
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

		userRepo.save(userEntity);

		// UNIQUE TOKENS
		String token = UUID.randomUUID().toString();

		//  Send verification email with subject + token
		emailService.emailSend(userEntity.getEmail(), CustomMsg.subject, token);

	}

}
