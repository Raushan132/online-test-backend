package com.test.service;

import com.test.model.UserEntity;

public interface IUserService {
	
	void saveUser(UserEntity userEntity);
	
	boolean userVerification(String token, Integer id);
	
	UserEntity getUserEntityByEmail(String email);


}
