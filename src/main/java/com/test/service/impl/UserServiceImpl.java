package com.test.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.constants.UserRoleConstant;
import com.test.dto.AuthDTO;
import com.test.email.CustomMsg;
import com.test.email.service.IEmailService;
import com.test.model.Role;
import com.test.model.UserEntity;
import com.test.model.UserIdAndTokenEntity;
import com.test.repository.RoleRepository;
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
	
	@Autowired
	private RoleRepository roleRepo;
	
	

	@Override
	public void saveUser(UserEntity userEntity) {

		Optional<UserEntity> temp = userRepo.findByEmail(userEntity.getEmail());
		if (temp.isPresent()) {
			throw new RuntimeException("User is Already Exist Exception");

		}
		userEntity.setRegisteredAt(LocalDate.now());
		Set<Role> roles = new HashSet<>();
		Role role= getRole(UserRoleConstant.USER);
		roles.add(role);
		
		userEntity.setActive(true);
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
			entity.setVerified(true);
			userRepo.save(entity);
			return true;
		}
		else {
			return false;
		}

	}



	@Override
	public UserEntity getUserEntityByEmail(String email) {
		
		return userRepo.findByEmail(email).orElseThrow(()->new  RuntimeException("User Not found Exception") );
	}
	
	/**
	 * Generate new Role if Role doesn't exist else  return Role from database
	 * @param userRole
	 * @return
	 */
	private Role getRole(UserRoleConstant userRole) {
	  Optional<Role> optRole =	roleRepo.findByRoleName(userRole.name());
	  if(optRole.isPresent()) return optRole.get();	  
	  Role role = new Role();
	  role.setRoleName(userRole.name());
	  role = roleRepo.save(role);
	  return role;
	  
		
	}

	@Override
	public AuthDTO getUserInfo(String email) {
		// TODO Auto-generated method stub
		AuthDTO authDTO = new AuthDTO();
		UserEntity userInfo = getUserEntityByEmail(email);
		authDTO.setUsername(userInfo.getName());
		Set<Role> roles = userInfo.getRoles();
		List<String> userRole = roles.stream().map(Role::getRoleName).toList();
		authDTO.setUserRole(userRole);
		return authDTO;
	}

	

}
