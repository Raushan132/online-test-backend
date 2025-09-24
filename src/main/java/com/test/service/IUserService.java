package com.test.service;


import com.test.dto.AuthDTO;
import com.test.model.UserEntity;

public interface IUserService {
	
	void saveUser(UserEntity userEntity);
	
	
	boolean userVerification(String token, Integer id);
	
	/**
	 * Get UserEntity By Given Email Id
	 * @param email
	 * @return
	 */
	UserEntity getUserEntityByEmail(String email);
	
	/**
	 * Get User Info like name and list of roles
	 * @param email
	 * @return
	 */
	AuthDTO getUserInfo(String email);
	



}
