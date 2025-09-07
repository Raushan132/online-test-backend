package com.test.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.email.CustomMsg;
import com.test.email.service.IEmailService;
import com.test.model.Role;
import com.test.model.UserEntity;
import com.test.model.UserIdAndTokenEntity;
import com.test.repository.UserIdAndTokenRepository;
import com.test.repository.UserRepository;
import com.test.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserIdAndTokenRepository userIdToken;
	
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
		Set<Role> roles = new HashSet<>();
		Role role= new Role();
		role.setRoleName("USER");
		roles.add(role);
		
		
		userEntity.setRoles(roles);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		// UNIQUE TOKENS
		String token = UUID.randomUUID().toString();
		
		userRepo.save(userEntity);
		
		UserIdAndTokenEntity idTokenEntity = new UserIdAndTokenEntity();
		idTokenEntity.setUserId(userEntity.getUserId());
		idTokenEntity.setToken(token);
		userIdToken.save(idTokenEntity);
		//  Send verification email with subject + token
		emailService.emailSend(userEntity.getEmail(), CustomMsg.subject, token,userEntity.getUserId());

	}
	
	
	
	@Override
	public boolean userVerification(String token, Integer id) {
		Optional<UserIdAndTokenEntity> byId = userIdToken.findByUserId(id);
		if(byId.isPresent()) {
			Optional<UserEntity> userEntity = userRepo.findById(id);
			UserEntity entity = userEntity.get();
			entity.setActive(true);
			userRepo.save(entity);
			return true;
		}
		else {
			return false;
		}

	}

}
