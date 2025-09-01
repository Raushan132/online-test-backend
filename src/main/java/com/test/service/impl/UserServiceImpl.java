package com.test.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.model.UserEntity;
import com.test.repository.UserRepository;
import com.test.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void saveUser(UserEntity userEntity) {
		
		Optional<UserEntity> temp=userRepo.findByEmail(userEntity.getEmail());
		if(temp.isPresent()) {
			throw new RuntimeException("User is Already Exist Exception");
			
		}
		userEntity.setRegisteredAt(LocalDate.now());
		userEntity.setRole("USER");
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		
		userRepo.save(userEntity);

	}

}
